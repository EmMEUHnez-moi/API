package com.willimath.api.service;

import com.willimath.api.data.*;
import com.willimath.api.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willimath.api.model.Trip;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UserTripRepository userTripRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    private List<UserTripEntity> getUserFromTrip(TripEntity tripEntity) {
        Optional<List<UserTripEntity>> userTrip = userTripRepository.findByTripId(tripEntity.getId());
        if(userTrip.isEmpty()) {
            throw  new TripNotFoundException(tripEntity.getId());
        }
        return userTrip.get();
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
        return trip;
    }

    public Trip getTrajetById(Integer tripId) {
        Optional<TripEntity> tripEntity = tripRepository.findById(tripId);
        if(tripEntity.isEmpty()) {
            throw new TripNotFoundException(tripId);
        }
        return new Trip(
                tripEntity.get().getId(),
                tripEntity.get().getFrom_location(),
                tripEntity.get().getTo_location(),
                tripEntity.get().getStart_date(),
                tripEntity.get().getEnd_date(),
                tripEntity.get().getHour_of_departure(),
                tripEntity.get().getHour_of_arrival(),
                tripEntity.get().getPrice(),
                tripEntity.get().getNumber_of_seats(),
                userService.getDriverFromTrip(tripId)
        );
    }

    public List<User> getPassagersByTrajet(Integer tripId) {
        Optional<List<UserTripEntity>> userTripEntity = userTripRepository.findByTripId(tripId);
        if(userTripEntity.isEmpty()) {
            throw new TripNotFoundException(tripId);
        }
        return userTripEntity.get().stream()
                .map(userTrip -> new User(
                        userTrip.getUser().getId(),
                        userTrip.getUser().getName(),
                        userTrip.getUser().getSurname(),
                        userTrip.getUser().getEmail()
                ))
                .collect(java.util.stream.Collectors.toList());
    }

    public void addPassagerToTrajet(Integer tripId, UUID userId) {
        Optional<TripEntity> tripEntity = tripRepository.findById(tripId);
        if(tripEntity.isEmpty()) {
            throw new TripNotFoundException(tripId);
        }
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if(userEntity.isEmpty()) {
            throw new UserNotFoundException(userId);
        }
        Optional<RoleEntity> roleEntity = roleRepository.findByName("Passager");
        if(roleEntity.isEmpty()) {
            throw new RoleNotFoundException("Passager");
        }
        userTripRepository.save(new UserTripEntity(
            userEntity.get(),
            tripEntity.get(),
            roleEntity.get()
        ));
    }
}
