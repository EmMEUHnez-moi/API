package com.willimath.api.model;

import java.time.LocalDate;
import java.util.UUID;

public record UserDetails(
        UUID id,
        String name,
        String surname,
        String email,
        LocalDate birth_date,
        String phone_number
) {
}
