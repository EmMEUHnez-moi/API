package com.willimath.api.service;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer id) {

        super("User with id " + id + " could not be found");
    }

    public UserNotFoundException(String name) {

        super("User " + name + " could not be found");
    }
}