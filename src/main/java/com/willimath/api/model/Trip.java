package com.willimath.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record Trip(
    @NotBlank(message = "Start location is mandatory") String from_location,
    @NotBlank(message = "End location is mandatory") String to_location,
    @NotBlank(message = "Start date is mandatory") @FutureOrPresent(message = "Start date must be present or future") LocalDate start_date,
    LocalDate end_date,
    @Schema(type = "string", format = "time", example = "HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @NotBlank(message = "Hour of departure is mandatory")
    LocalTime hour_of_departure,
    @Schema(type = "string", format = "time", example = "HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm:ss")
    LocalTime hour_of_arrival,
    @NotBlank(message = "Pris is mandatory") Float price,
    @NotBlank(message = "Number of seats is mandatory") Integer number_of_seats,
    List<UserFromTrip> people
) {
}
