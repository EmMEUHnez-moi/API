package com.willimath.api.service;

import com.willimath.api.data.TripEntity;
import com.willimath.api.data.UserTripEntity;
import com.willimath.api.data.UserTripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willimath.api.data.TripRepository;
import com.willimath.api.Trip;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UserTripRepository userTripRepository;

    public List<Trip> getTrajet(String villedepart, String villearrivee, String date) {
        Optional<List<TripEntity>> trip = tripRepository.findByLocationAndStart_date(
                villedepart,
                villearrivee,
                LocalDate.parse(date));
        if(trip.isEmpty()) {
            throw  new RuntimeException("No trip found");
        }
        return trip.get().stream()
                .map(tripEntity -> new Trip(
                        tripEntity.getFrom_location(),
                        tripEntity.getTo_location(),
                        tripEntity.getStart_date(),
                        tripEntity.getEnd_date(),
                        tripEntity.getHour_of_departure(),
                        tripEntity.getHour_of_arrival(),
                        tripEntity.getPrice(),
                        tripEntity.getNumber_of_seats()
                ))
                .toList();
    }

    public Trip createTrajet(Trip trip) {
        tripRepository.save(new TripEntity(
            trip.from_location(),
            trip.to_location(),
            trip.start_date(),
            trip.end_date(),
            trip.hour_of_departure(),
            trip.hour_of_arrival(),
            trip.price(),
            trip.number_of_seats()
        ));
        return trip;
    }

    private Trip convert(TripEntity tripentiry) {
        return new Trip(
                tripentiry.getFrom_location(),
                tripentiry.getTo_location(),
                tripentiry.getStart_date(),
                tripentiry.getEnd_date(),
                tripentiry.getHour_of_departure(),
                tripentiry.getHour_of_arrival(),
                tripentiry.getPrice(),
                tripentiry.getNumber_of_seats()
        );
    }

    public List<Trip> getTrajetByUser(Integer userid) {
        Optional<List<UserTripEntity>> trip = userTripRepository.findByUserId(userid);
        if(trip.isEmpty()) {
            throw  new RuntimeException("No trip found");
        }
        return trip.get().stream()
                .map(userTripEntity -> convert(userTripEntity.getTrip()))
                .toList();

    }
}
