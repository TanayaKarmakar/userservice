package com.app.userservice.controllers.advice;

import com.app.userservice.constants.AppConstants;
import com.app.userservice.exceptions.BusinessException;
import com.app.userservice.exceptions.BusinessValidationException;
import com.app.userservice.exceptions.NotFoundException;
import com.app.userservice.exceptions.UserAPIException;
import com.app.userservice.models.dtos.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class UserServiceControllerAdvice {
    @ExceptionHandler(value = {UserAPIException.class})
    protected ResponseEntity<ErrorResponse> handleConflict(
            Exception ex, WebRequest request) {
        UserAPIException userAPIException = (UserAPIException) ex;
        BusinessException businessException = userAPIException.getBusinessException();
        ErrorResponse errorResponse = buildErrorResponse(businessException.getException());

        HttpStatus status = getStatusCode(businessException.getException());
        errorResponse.setStatus(status);
        return new ResponseEntity<>(errorResponse, status);
    }

    private ErrorResponse buildErrorResponse(Exception exception) {
        String[] tokens = exception.getMessage().split(AppConstants.COLON_DELIM);
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode(tokens[0]);
        errorResponse.setDescription(tokens[1]);
        return errorResponse;
    }

    private HttpStatus getStatusCode(Exception exception) {
        if(exception instanceof NotFoundException)
            return HttpStatus.NOT_FOUND;
        else if(exception instanceof BusinessValidationException)
            return HttpStatus.BAD_REQUEST;
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
