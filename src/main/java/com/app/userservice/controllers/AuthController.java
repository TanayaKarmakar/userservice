package com.app.userservice.controllers;

import com.app.userservice.exceptions.BusinessException;
import com.app.userservice.exceptions.UserAPIException;
import com.app.userservice.models.dtos.LoginRequestDTO;
import com.app.userservice.models.dtos.SessionDTO;
import com.app.userservice.models.dtos.SignupRequestDTO;
import com.app.userservice.models.dtos.UserDTO;
import com.app.userservice.services.AuthService;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class AuthController {
    private static Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @PostMapping("/users")
    public ResponseEntity<UserDTO> createNewUser(@RequestBody SignupRequestDTO signupRequestDTO) throws UserAPIException {
        try {
            UserDTO createdUser = authService.createNewUser(signupRequestDTO);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (BusinessException businessException) {
            logger.error("An error occurred while trying to create the user: {}", signupRequestDTO);
            throw new UserAPIException(businessException);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) throws UserAPIException {
        try {
            Pair<UserDTO, String> loggedInUser = authService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
            MultiValueMapAdapter<String, String> httpHeaders = new MultiValueMapAdapter<>(new HashMap<>());
            httpHeaders.add(HttpHeaders.SET_COOKIE, "auth-token:"+httpHeaders);
            return new ResponseEntity<>(loggedInUser.getLeft(), httpHeaders, HttpStatus.OK);
        } catch (BusinessException businessException) {
            logger.error("An error occurred while trying to login the user: {}", loginRequestDTO.getEmail());
            throw new UserAPIException(businessException);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<UserDTO> logout(@RequestBody SessionDTO sessionDTO) throws UserAPIException {
        try {
            UserDTO loggedOutUser = authService.logout(sessionDTO);
            return new ResponseEntity<>(loggedOutUser, HttpStatus.OK);
        } catch (BusinessException businessException) {
            logger.error("An error occurred while trying to logout the user: {}", sessionDTO.getUserId());
            throw new UserAPIException(businessException);
        }
    }
}
