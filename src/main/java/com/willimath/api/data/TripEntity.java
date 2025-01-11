package com.willimath.api.data;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "trip", schema = "public")
public class TripEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "from_location")
    private String from_location;

    @Column(name = "to_location")
    private String to_location;

    @Column(name = "start_date")
    private LocalDate start_date;

    @Column(name = "end_date")
    private LocalDate end_date;

    @Column(name = "hour_of_departure")
    private LocalTime hour_of_departure;

    @Column(name = "hour_of_arrival")
    private LocalTime hour_of_arrival;

    @Column(name = "price")
    private float price;

    @Column(name = "number_of_seats")
    private int number_of_seats;

    public TripEntity() {
    }

    public TripEntity(String from_location, String to_location, LocalDate start_date, LocalDate end_date, LocalTime hour_of_departure, LocalTime hour_of_arrival, float price, int number_of_seats) {
        this.from_location = from_location;
        this.to_location = to_location;
        this.start_date = start_date;
        this.end_date = end_date;
        this.hour_of_departure = hour_of_departure;
        this.hour_of_arrival = hour_of_arrival;
        this.price = price;
        this.number_of_seats = number_of_seats;
    }

    public TripEntity(Integer id, String from_location, String to_location, LocalDate start_date, LocalDate end_date, LocalTime hour_of_departure, LocalTime hour_of_arrival, float price, int number_of_seats) {
        this.id = id;
        this.from_location = from_location;
        this.to_location = to_location;
        this.start_date = start_date;
        this.end_date = end_date;
        this.hour_of_departure = hour_of_departure;
        this.hour_of_arrival = hour_of_arrival;
        this.price = price;
        this.number_of_seats = number_of_seats;
    }

    public int getId() {
        return id;
    }

    public String getFrom_location() {
        return from_location;
    }

    public void setFrom_location(String from_location) {
        this.from_location = from_location;
    }

    public String getTo_location() {
        return to_location;
    }

    public void setTo_location(String to_location) {
        this.to_location = to_location;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public LocalTime getHour_of_departure() {
        return hour_of_departure;
    }

    public void setHour_of_departure(LocalTime hour_of_departure) {
        this.hour_of_departure = hour_of_departure;
    }

    public LocalTime getHour_of_arrival() {
        return hour_of_arrival;
    }

    public void setHour_of_arrival(LocalTime hour_of_arrival) {
        this.hour_of_arrival = hour_of_arrival;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNumber_of_seats() {
        return number_of_seats;
    }

    public void setNumber_of_seats(int number_of_seats) {
        this.number_of_seats = number_of_seats;
    }
}
