package com.app.userservice.models.dtos;

import com.app.userservice.models.entities.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class UserDTO {
    private String id;
    private String email;
    private Set<RoleDTO> roles;


    public static UserDTO from(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setRoles(user.getRoles().stream().map(role -> RoleDTO.from(role)).collect(Collectors.toSet()));
        return userDTO;
    }
}
