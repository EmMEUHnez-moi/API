package com.willimath.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalTime;

public record Trip(
    Integer id,
    String from_location,
    String to_location,
    @Schema(type = "string", format = "date", example = "yyyy-MM-dd")
    LocalDate start_date,
    @Schema(type = "string", format = "date", example = "yyyy-MM-dd")
    LocalDate end_date,
    @Schema(type = "string", format = "time", example = "HH:mm:ss")
    LocalTime hour_of_departure,
    @Schema(type = "string", format = "time", example = "HH:mm:ss")
    LocalTime hour_of_arrival,
    Float price,
    Integer number_of_seats,
    User driver
) {
}
