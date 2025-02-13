package com.willimath.api.model;

public record User(
        Integer id,
        String name,
        String surname,
        String email
) {
}
