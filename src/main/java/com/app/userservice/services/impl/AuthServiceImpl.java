package com.app.userservice.services.impl;

import com.app.userservice.constants.ErrorCodes;
import com.app.userservice.exceptions.BusinessException;
import com.app.userservice.exceptions.BusinessValidationException;
import com.app.userservice.exceptions.InvalidLoginException;
import com.app.userservice.exceptions.NotFoundException;
import com.app.userservice.helpers.ErrorHandlerHelper;
import com.app.userservice.models.dtos.SessionDTO;
import com.app.userservice.models.dtos.SignupRequestDTO;
import com.app.userservice.models.dtos.UserDTO;
import com.app.userservice.models.entities.Session;
import com.app.userservice.models.entities.SessionStatus;
import com.app.userservice.models.entities.User;
import com.app.userservice.models.mappers.ModelMapper;
import com.app.userservice.repositories.SessionRepository;
import com.app.userservice.repositories.UserRepository;
import com.app.userservice.helpers.BusinessValidationHelper;
import com.app.userservice.services.AuthService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.tuple.Pair;
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
    public UserDTO createNewUser(SignupRequestDTO signupRequestDTO) throws BusinessException  {
        try {
            logger.info("User creation started for the user: {}", signupRequestDTO);
            if(businessValidationHelper.isEmailExists(signupRequestDTO.getEmail())) {
                logger.error("User with email: {} already exeits ", signupRequestDTO.getEmail());
                throw new BusinessValidationException(ErrorCodes.USER_EMAIL_EXISTS);
            }
            User createdUser = modelMapper.toNewUser(signupRequestDTO);
            userRepository.save(createdUser);
            logger.info("User creation finished for the user: {}", signupRequestDTO);
            return UserDTO.from(createdUser);
        } catch (Exception exception) {
            logger.error("An error occurred while trying to to create user: {}", signupRequestDTO, exception);
            throw errorHandlerHelper.buildBusinessException(exception);
        }
    }

    @Override
    public Pair<UserDTO, String> login(String email, String password) throws BusinessException {

        try {
            logger.info("Login activity started for the user: {}", email);
            // if the user has two sessions throw error (kind of following scaler)
            Optional<User> userOptional = userRepository.findByEmail(email);
            if(userOptional.isEmpty()) {
                logger.error("User with email: {} not found", email);
                throw new NotFoundException(ErrorCodes.NOT_FOUND);
            }

            List<Session> currentActiveSessions = sessionRepository.findByUser(userOptional.get());
            if(currentActiveSessions.size() == 2) {
                logger.error("User with ID: {} has two active sessions: {}", email, currentActiveSessions);
                throw new BusinessValidationException(ErrorCodes.TO_MANY_SESSIONS + currentActiveSessions);
            }

            if(!userOptional.get().getEncryptedPass().equals(password)) {
                logger.error("User: {} has logged in with incorrect credentials", email);
                throw new InvalidLoginException(ErrorCodes.INCORRECT_PASSWORD);
            }
            Session session = new Session();
            String token = RandomStringUtils.randomAlphanumeric(30);
            session.setToken(token);
            session.setUser(userOptional.get());
            session.setStatus(SessionStatus.ACTIVE);
            sessionRepository.save(session);
            logger.info("Login activity finished for the user: {}", email);
            return Pair.of(UserDTO.from(userOptional.get()), token);
        } catch (Exception exception) {
            logger.error("An error occurred while trying to login user: {}", email, exception);
            throw errorHandlerHelper.buildBusinessException(exception);
        }
    }

    @Override
    public UserDTO logout(SessionDTO sessionDTO) throws BusinessException {
//        try {
//            User currentUser = getUser(sessionDTO.getUserId());
//            Optional<Session> sessionOptional = sessionRepository.findById(UUID.fromString(sessionDTO.getSessionId()));
//            if(sessionOptional.isEmpty()) {
//                logger.error("Session with ID: {} not found", sessionDTO.getSessionId());
//                throw new NotFoundException(ErrorCodes.NOT_FOUND);
//            }
//            sessionRepository.delete(sessionOptional.get());
//            return modelMapper.toUserDTO(currentUser);
//        } catch (Exception exception) {
//            logger.error("An error occurred while trying to logout user: {}", sessionDTO.getUserId(), exception);
//            throw errorHandlerHelper.buildBusinessException(exception);
//        }
        return null;
    }

    private User getUser(String userID) {
//        Optional<User> currentUser = userRepository.findById(UUID.fromString(userID));
//        if(currentUser.isEmpty()) {
//            logger.error("User with ID: {} not exists ", userID);
//            throw new NotFoundException(ErrorCodes.NOT_FOUND);
//        }
//        return currentUser.get();
        return null;
    }
}
