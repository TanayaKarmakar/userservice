package com.app.userservice.controllers;

import com.app.userservice.exceptions.BusinessException;
import com.app.userservice.exceptions.UserAPIException;
import com.app.userservice.models.dtos.RoleDTO;
import com.app.userservice.services.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RollController {
    @Autowired
    private RoleService roleService;

    private static Logger logger = LoggerFactory.getLogger(RollController.class);


    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) throws UserAPIException {
        try {
            RoleDTO createdRole = roleService.createRole(roleDTO);
            return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
        } catch (BusinessException businessException) {
            logger.error("An error occurred while trying create the role: {}", roleDTO);
            throw new UserAPIException(businessException);
        }
    }
}
