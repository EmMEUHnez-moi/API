package com.willimath.api.model;

import java.time.LocalDate;

public record UserDetails(
        Integer id,
        String name,
        String surname,
        String email,
        LocalDate birth_date,
        String phone_number
) {
}
