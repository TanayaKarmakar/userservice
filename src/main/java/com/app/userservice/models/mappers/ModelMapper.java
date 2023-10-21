package com.app.userservice.models.mappers;

import com.app.userservice.models.dtos.SessionDTO;
import com.app.userservice.models.dtos.SignupRequestDTO;
import com.app.userservice.models.dtos.UserDTO;
import com.app.userservice.models.entities.Session;
import com.app.userservice.models.entities.User;
import org.springframework.stereotype.Component;


@Component
public class ModelMapper {
    public User toNewUser(SignupRequestDTO signupRequestDTO) {
        User user = new User();
        user.setEmail(signupRequestDTO.getEmail());
        return user;
    }

    public UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
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
