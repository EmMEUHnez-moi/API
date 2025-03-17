package com.willimath.api.web;

import com.willimath.api.service.TripAlreadyExistsException;
import com.willimath.api.service.TripNotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TripErrorHandler {
    @ExceptionHandler(TripNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handleTripNotFoundException(TripNotFoundException ex) {
        return new Error(ex.getMessage());
    }

    @ExceptionHandler(TripAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleTripAlreadyExistsException(TripAlreadyExistsException ex) {
        return new Error(ex.getMessage());
    }
}