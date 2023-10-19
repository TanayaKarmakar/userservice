package com.app.userservice.services;

import com.app.userservice.exceptions.BusinessException;
import com.app.userservice.models.dtos.SessionDTO;
import com.app.userservice.models.dtos.UserDTO;

public interface AuthService {
    UserDTO createNewUser(UserDTO userDTO) throws BusinessException;

    SessionDTO login(UserDTO userDTO) throws BusinessException;

    UserDTO logout(SessionDTO sessionDTO) throws BusinessException;
}
