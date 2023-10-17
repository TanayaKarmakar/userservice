package com.app.userservice.helpers;

import com.app.userservice.models.dtos.UserDTO;
import com.app.userservice.models.entities.User;
import com.app.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BusinessValidationHelper {
    @Autowired
    private UserRepository userRepository;

    public boolean isEmailExists(UserDTO userDTO) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(userDTO.getEmail()));
        return !userOptional.isEmpty();
    }
}
