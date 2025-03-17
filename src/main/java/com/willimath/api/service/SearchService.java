package com.willimath.api.service;

import com.willimath.api.data.TripEntity;
import com.willimath.api.data.TripRepository;
import com.willimath.api.data.UserEntity;
import com.willimath.api.data.UserRepository;
import com.willimath.api.model.Trip;
import com.willimath.api.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private static final Logger log = LoggerFactory.getLogger(SearchService.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private UserService userService;

    public User getUserByName(String name) {
        log.info("Invoking getUserByName with name={}",name);
        Optional<UserEntity> userEntity =  userRepository.findByNameIgnoreCase(name);
        if(userEntity.isEmpty()) {
            log.warn("Could not find user with name={}",name);
            throw new UserNotFoundException(name);
        }
        return new User(
                userEntity.get().getId(),
                userEntity.get().getName(),
                userEntity.get().getSurname(),
                userEntity.get().getEmail()
        );
    }

    public List<Trip> getTrajet(String villedepart, String villearrivee, String date) {
        log.info("Invoking getTrajet with villedepart={} and villearrivee={} and date={}",villedepart,villearrivee,date);
        Optional<List<TripEntity>> trip = tripRepository.findByLocationAndStart_date(
                villedepart,
                villearrivee,
                LocalDate.parse(date));
        if(trip.isEmpty()) {
            log.warn("Could not find trip with villedepart={}and villearrivee={} and date={}",villedepart,villearrivee,date);
            throw  new TripNotFoundException(villedepart, villearrivee, date);
        }
        return trip.get().stream()
                .map(tripEntity -> new Trip(
                        tripEntity.getId(),
                        tripEntity.getFrom_location(),
                        tripEntity.getTo_location(),
                        tripEntity.getStart_date(),
                        tripEntity.getEnd_date(),
                        tripEntity.getHour_of_departure(),
                        tripEntity.getHour_of_arrival(),
                        tripEntity.getPrice(),
                        tripEntity.getNumber_of_seats(),
                        userService.getDriverFromTrip(tripEntity.getId())
                ))
                .collect(Collectors.toList());
    }

}
