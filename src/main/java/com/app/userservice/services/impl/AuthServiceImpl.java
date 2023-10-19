package com.app.userservice.services.impl;

import com.app.userservice.constants.ErrorCodes;
import com.app.userservice.exceptions.BusinessException;
import com.app.userservice.exceptions.BusinessValidationException;
import com.app.userservice.exceptions.NotFoundException;
import com.app.userservice.helpers.ErrorHandlerHelper;
import com.app.userservice.models.dtos.SessionDTO;
import com.app.userservice.models.dtos.UserDTO;
import com.app.userservice.models.entities.Session;
import com.app.userservice.models.entities.User;
import com.app.userservice.models.mappers.ModelMapper;
import com.app.userservice.repositories.SessionRepository;
import com.app.userservice.repositories.UserRepository;
import com.app.userservice.helpers.BusinessValidationHelper;
import com.app.userservice.services.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {
    private static Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private BusinessValidationHelper businessValidationHelper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ErrorHandlerHelper errorHandlerHelper;

    @Override
    public UserDTO createNewUser(UserDTO userDTO) throws BusinessException  {
        try {
            logger.info("User creation started for the user: {}", userDTO);
            if(businessValidationHelper.isEmailExists(userDTO)) {
                logger.error("User with email: {} already exeits ", userDTO.getEmail());
                throw new BusinessValidationException(ErrorCodes.USER_EMAIL_EXISTS);
            }
            User createdUser = modelMapper.toUser(userDTO);
            userRepository.save(createdUser);
            logger.info("User creation finished for the user: {}", userDTO);
            return modelMapper.toUserDTO(createdUser);
        } catch (Exception exception) {
            logger.error("An error occurred while trying to to create user: {}", userDTO, exception);
            throw errorHandlerHelper.buildBusinessException(exception);
        }
    }

    @Override
    public SessionDTO login(UserDTO userDTO) throws BusinessException {
        try {
            logger.info("Login activity started for the user: {}", userDTO);
            // if the user has two sessions throw error (kind of following scaler)
            User currentUser = getUser(userDTO.getId());
            List<Session> currentActiveSessions = sessionRepository.findByUser(currentUser);
            if(currentActiveSessions.size() == 2) {
                logger.error("User with ID: {} has two active sessions: {}", userDTO.getId(), currentActiveSessions);
                throw new BusinessValidationException(ErrorCodes.TO_MANY_SESSIONS + currentActiveSessions);
            }
            Session session = new Session();
            session.setId(UUID.randomUUID());
            session.setToken(UUID.randomUUID().toString());
            session.setUser(currentUser);
            sessionRepository.save(session);
            logger.info("Login activity finished for the user: {}", userDTO);
            return modelMapper.toSessionDTO(session);
        } catch (Exception exception) {
            logger.error("An error occurred while trying to login user: {}", userDTO, exception);
            throw errorHandlerHelper.buildBusinessException(exception);
        }
    }

    @Override
    public UserDTO logout(SessionDTO sessionDTO) throws BusinessException {
        try {
            User currentUser = getUser(sessionDTO.getUserId());
            Optional<Session> sessionOptional = sessionRepository.findById(UUID.fromString(sessionDTO.getSessionId()));
            if(sessionOptional.isEmpty()) {
                logger.error("Session with ID: {} not found", sessionDTO.getSessionId());
                throw new NotFoundException(ErrorCodes.NOT_FOUND);
            }
            sessionRepository.delete(sessionOptional.get());
            return modelMapper.toUserDTO(currentUser);
        } catch (Exception exception) {
            logger.error("An error occurred while trying to logout user: {}", sessionDTO.getUserId(), exception);
            throw errorHandlerHelper.buildBusinessException(exception);
        }
    }

    private User getUser(String userID) {
        Optional<User> currentUser = userRepository.findById(UUID.fromString(userID));
        if(currentUser.isEmpty()) {
            logger.error("User with ID: {} not exists ", userID);
            throw new NotFoundException(ErrorCodes.NOT_FOUND);
        }
        return currentUser.get();
    }
}
