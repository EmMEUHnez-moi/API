package com.willimath.api;

public record User(
    String name,
    String surname,
    String email,
    String password
) {
}
