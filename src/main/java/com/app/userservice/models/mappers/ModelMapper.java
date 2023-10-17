package com.app.userservice.models.mappers;

import com.app.userservice.models.dtos.SessionDTO;
import com.app.userservice.models.dtos.UserDTO;
import com.app.userservice.models.entities.Session;
import com.app.userservice.models.entities.User;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class ModelMapper {
    public User toUser(UserDTO userDTO) {
        User user = new User();
        if(!Objects.isNull(userDTO.getId())) {
            user.setId(UUID.fromString(userDTO.getId()));
        } else {
            user.setId(UUID.randomUUID());
        }
        user.setEmail(userDTO.getEmail());
        user.setEncryptedPass(userDTO.getEncryptedPass());
        return user;
    }

    public UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId().toString());
        userDTO.setEmail(user.getEmail());
        userDTO.setEncryptedPass(user.getEncryptedPass());
        return userDTO;
    }

    public SessionDTO toSessionDTO(Session session) {
        SessionDTO sessionDTO = new SessionDTO();
        sessionDTO.setSessionId(session.getId().toString());
        sessionDTO.setSessionId(session.getToken());
        sessionDTO.setUserId(session.getUser().getId().toString());
        return sessionDTO;
    }

}
