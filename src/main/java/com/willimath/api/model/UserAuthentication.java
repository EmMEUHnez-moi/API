package com.willimath.api.model;

public record UserAuthentication(
        String login,
        String token
) {
}
