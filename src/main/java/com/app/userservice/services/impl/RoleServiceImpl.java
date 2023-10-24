package com.app.userservice.services.impl;

import com.app.userservice.utils.constants.ErrorCodes;
import com.app.userservice.exceptions.BusinessException;
import com.app.userservice.exceptions.BusinessValidationException;
import com.app.userservice.helpers.ErrorHandlerHelper;
import com.app.userservice.models.dtos.RoleDTO;
import com.app.userservice.models.entities.Role;
import com.app.userservice.repositories.RoleRepository;
import com.app.userservice.services.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ErrorHandlerHelper errorHandlerHelper;

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) throws BusinessException {
        try {
            Optional<Role> roleOptional = roleRepository.findByRole(roleDTO.getName());
            if(roleOptional.isPresent()) {
                logger.error("A role with name: {} already exists in the system, please create a different role ", roleDTO.getName());
                throw new BusinessValidationException(ErrorCodes.DUPLICATE_ROLE);
            }
            Role role = new Role();
            role.setRole(roleDTO.getName());
            roleRepository.save(role);
            return RoleDTO.from(role);
        } catch (Exception exception) {
            logger.error("An error occurred while trying to create the role: {} in the system", roleDTO.getName());
            throw errorHandlerHelper.buildBusinessException(exception);
        }
    }
}
