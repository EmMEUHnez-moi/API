package com.willimath.api.service;

import com.willimath.api.model.User;

public class TripNotFoundException extends RuntimeException {
    public TripNotFoundException(Integer userid) {
        super("Trip with user "+ userid + " could not be found");
    }

    public TripNotFoundException(String villedepart, String villearrivee, String date) {
        super("Trip with location "+ villedepart + " to " + villearrivee + " on " + date + " could not be found");
    }
}