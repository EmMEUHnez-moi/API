package com.willimath.api.service;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UUID id) {

        super("User with id " + id + " could not be found");
    }

    public UserNotFoundException(String name) {

        super("User " + name + " could not be found");
    }
}