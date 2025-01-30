package com.willimath.api.service;

import com.willimath.api.Role;
import com.willimath.api.User;
import com.willimath.api.UserFromTrip;
import com.willimath.api.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willimath.api.Trip;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UserTripRepository userTripRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private List<UserTripEntity> getUserFromTrip(TripEntity tripEntity) {
        Optional<List<UserTripEntity>> userTrip = userTripRepository.findByTripId(tripEntity.getId());
        if(userTrip.isEmpty()) {
            throw  new RuntimeException("No user found");
        }
        return userTrip.get();
    }

    public List<Trip> getTrajet(String villedepart, String villearrivee, String date) {
        Optional<List<TripEntity>> trip = tripRepository.findByLocationAndStart_date(
                villedepart,
                villearrivee,
                LocalDate.parse(date));
        if(trip.isEmpty()) {
            throw  new RuntimeException("No trip found");
        }
        return trip.get().stream()
                .map(tripEntity -> convert(tripEntity, getUserFromTrip(tripEntity)))
                .toList();
    }

    public Trip createTrajet(Trip trip) {
        TripEntity TE = tripRepository.save(new TripEntity(
            trip.from_location(),
            trip.to_location(),
            trip.start_date(),
            trip.end_date(),
            trip.hour_of_departure(),
            trip.hour_of_arrival(),
            trip.price(),
            trip.number_of_seats()
        ));
        for (UserFromTrip user : trip.people()) {
            userTripRepository.save(new UserTripEntity(
                new UserEntity(
                        userRepository.findByEmail(user.user().email()).get().getId(),
                        user.user().name(),
                        user.user().surname(),
                        user.user().email(),
                        user.user().password()
                ),
                TE,
                roleRepository.findByName(user.role().name()).get()
            ));
        }
        return trip;
    }

    private Trip convert(TripEntity tripentiry, List<UserTripEntity> people) {
        return new Trip(
                tripentiry.getFrom_location(),
                tripentiry.getTo_location(),
                tripentiry.getStart_date(),
                tripentiry.getEnd_date(),
                tripentiry.getHour_of_departure(),
                tripentiry.getHour_of_arrival(),
                tripentiry.getPrice(),
                tripentiry.getNumber_of_seats(),
                people.stream()
                        .map(userTripEntity -> new UserFromTrip(
                                new User(
                                        userTripEntity.getUser().getName(),
                                        userTripEntity.getUser().getSurname(),
                                        userTripEntity.getUser().getEmail(),
                                        userTripEntity.getUser().getPassword()
                                ),
                                new Role(userTripEntity.getRole().getName())
                        )
                ).toList()
        );
    }

    public List<Trip> getTrajetByUser(Integer userid) {
        Optional<List<UserTripEntity>> trip = userTripRepository.findByUserId(userid);
        if(trip.isEmpty()) {
            throw  new RuntimeException("No trip found");
        }
        return trip.get().stream()
                .map(userTripEntity -> convert(userTripEntity.getTrip(), List.of(userTripEntity)))
                .toList();

    }
}
