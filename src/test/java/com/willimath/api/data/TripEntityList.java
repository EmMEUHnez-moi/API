package com.willimath.api.data;

import java.time.LocalDate;
import java.time.LocalTime;

public class TripEntityList {
    public static TripEntity PARIS = new TripEntity(1,
            
        "Paris", "Lyon", LocalDate.of(2025,01,20), LocalDate.of(2025,01,20), LocalTime.of(8,00), LocalTime.of(12,00), 50.00F, 3);

    public static TripEntity LILLE = new TripEntity(2,
        "Lille", "Bruxelles", LocalDate.of(2025,01,22), LocalDate.of(2025,01,22), LocalTime.of(10,00), LocalTime.of(11,30), 20.00F, 4);

    public static TripEntity MARSEILLE = new TripEntity(3,
            "Marseille", "Nice",  LocalDate.of(2025,01,25), LocalDate.of(2025,01,25), LocalTime.of(9,30), LocalTime.of(11,00), 30.00F, 2);

}
