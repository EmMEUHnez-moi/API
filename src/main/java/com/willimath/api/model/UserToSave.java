package com.willimath.api.model;

import jakarta.validation.constraints.NotBlank;

public record UserToSave(
        @NotBlank(message ="Name is mandatory") String name,
        @NotBlank(message = "Surname is mandatory") String surname,
        @NotBlank(message = "Email is mandatory") String email,
        @NotBlank(message = "Password is mandatory") String password
) {
}
