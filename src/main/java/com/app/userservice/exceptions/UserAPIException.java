package com.app.userservice.exceptions;

import lombok.Getter;

@Getter
public class UserAPIException extends Exception {
    private BusinessException businessException;
    public UserAPIException(BusinessException businessException) {
        this.businessException = businessException;
    }
}
