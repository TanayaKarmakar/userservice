package com.app.userservice.services;

import com.app.userservice.exceptions.BusinessException;
import com.app.userservice.models.dtos.SessionDTO;
import com.app.userservice.models.dtos.SignupRequestDTO;
import com.app.userservice.models.dtos.UserDTO;
import org.apache.commons.lang3.tuple.Pair;

public interface AuthService {
    UserDTO createNewUser(SignupRequestDTO signupRequestDTO) throws BusinessException;

    Pair<UserDTO, String> login(String email, String password) throws BusinessException;

    UserDTO logout(SessionDTO sessionDTO) throws BusinessException;
}
