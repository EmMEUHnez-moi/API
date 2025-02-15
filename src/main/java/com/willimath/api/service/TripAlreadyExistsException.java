package com.willimath.api.service;

public class TripAlreadyExistsException extends RuntimeException {
    public TripAlreadyExistsException(String message) {
        super(message);
    }
}