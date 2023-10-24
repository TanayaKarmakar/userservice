package com.app.userservice.services;

import com.app.userservice.exceptions.BusinessException;
import com.app.userservice.models.dtos.RoleDTO;

public interface RoleService {
    RoleDTO createRole(RoleDTO roleDTO) throws BusinessException;
}
