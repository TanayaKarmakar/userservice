package com.app.userservice.controllers;

import com.app.userservice.exceptions.BusinessException;
import com.app.userservice.exceptions.UserAPIException;
import com.app.userservice.models.dtos.SessionDTO;
import com.app.userservice.models.dtos.UserDTO;
import com.app.userservice.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserDTO> createNewUser(@RequestBody UserDTO userDTO) throws UserAPIException {
        try {
            UserDTO createdUser = userService.createNewUser(userDTO);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (BusinessException businessException) {
            logger.error("An error occurred while trying to create the user: {}", userDTO);
            throw new UserAPIException(businessException);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<SessionDTO> login(@RequestBody UserDTO userDTO) throws UserAPIException {
        try {
            SessionDTO loggedInSession = userService.login(userDTO);
            return new ResponseEntity<>(loggedInSession, HttpStatus.OK);
        } catch (BusinessException businessException) {
            logger.error("An error occurred while trying to login the user: {}", userDTO);
            throw new UserAPIException(businessException);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<UserDTO> logout(@RequestBody SessionDTO sessionDTO) throws UserAPIException {
        try {
            UserDTO loggedOutUser = userService.logout(sessionDTO);
            return new ResponseEntity<>(loggedOutUser, HttpStatus.OK);
        } catch (BusinessException businessException) {
            logger.error("An error occurred while trying to logout the user: {}", sessionDTO.getUserId());
            throw new UserAPIException(businessException);
        }
    }
}
