package com.willimath.api.service;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(Integer id){
        super("User with id " + id + " already exists.");
    }
}