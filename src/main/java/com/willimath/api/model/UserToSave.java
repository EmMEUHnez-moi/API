package com.willimath.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record UserToSave(
        @NotBlank(message ="Name is mandatory") String name,
        @NotBlank(message = "Surname is mandatory") String surname,
        @NotBlank(message = "Email is mandatory") String email,
        @NotBlank(message = "Password is mandatory") String password,
        @Schema(type = "string", format = "date", example = "yyyy-MM-dd")
        @NotBlank(message = "Birth date is mandatory")
        @PastOrPresent(message = "Birth date must be present or past")
        LocalDate birth_date,
        @NotBlank(message = "Phone number is mandatory") String phone_number
        // Date de naissance, numéro de téléphone, (genre)
) {
}
