package com.willimath.api.model;

import java.util.UUID;

public record UserFromTrip(
        UUID userId,
        Role role
) {
}
