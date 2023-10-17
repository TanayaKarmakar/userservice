package com.app.userservice.helpers;

import com.app.userservice.exceptions.BusinessException;
import org.springframework.stereotype.Component;

@Component
public class ErrorHandlerHelper {
    public BusinessException buildBusinessException(Exception exception) {
        BusinessException businessException = new BusinessException(exception);
        return businessException;
    }
}
