package com.willimath.api.service;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer id) {

        super("User with id " + id + " could not be found");
    }
}