package com.app.userservice.exceptions;

import lombok.Getter;

@Getter
public class BusinessException extends Exception {
    private Exception exception;

    public BusinessException(Exception exception) {
        this.exception = exception;
    }
}
