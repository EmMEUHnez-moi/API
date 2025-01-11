package com.willimath.api;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public record Trip(
    String from_location,
    String to_location,
    LocalDate start_date,
    LocalDate end_date,
    @JsonFormat(pattern = "HH:mm:ss")
    LocalTime hour_of_departure,
    @JsonFormat(pattern = "HH:mm:ss")
    LocalTime hour_of_arrival,
    float price,
    int number_of_seats
) {
}
