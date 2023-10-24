package com.app.userservice.models.dtos;

import com.app.userservice.models.entities.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RoleDTO {
    private Long id;
    private String name;

    public static RoleDTO from(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getRole());
        return roleDTO;
    }
}
