package com.app.userservice.models.dtos;

import com.app.userservice.models.entities.Role;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoleDTO {
    private String roleName;
    private Long id;

    public static RoleDTO from(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleName(role.getRole());
        roleDTO.setId(role.getId());
        return roleDTO;
    }
}
