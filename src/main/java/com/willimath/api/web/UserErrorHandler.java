package com.willimath.api.web;

import com.willimath.api.service.UserAlreadyExistsException;
import com.willimath.api.service.UserNotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserErrorHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handleUserNotFoundException(UserNotFoundException ex) {
        return new Error(ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return new Error(ex.getMessage());
    }
}