package com.willimath.api.service;

import com.willimath.api.data.UserTripEntity;
import com.willimath.api.data.UserTripRepository;
import com.willimath.api.data.UserEntity;
import com.willimath.api.data.UserRepository;
import com.willimath.api.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTripRepository userTripRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        log.info("Invoking getAllUsers");
        return userRepository.findAll().stream()
                .map(userEntity -> new User(
                        userEntity.getId(),
                        userEntity.getName(),
                        userEntity.getSurname(),
                        userEntity.getEmail()
                ))
                .collect(Collectors.toList());
    }

    public UserToSave createUser(UserToSave user) {
        log.info("Invoking createUser with userToSave={}",user);
        userRepository.save(new UserEntity(
            user.name(),
            user.surname(),
            user.email(),
            user.password(),
            user.birth_date(),
            user.phone_number()
        ));
        return user;
    }

    public UserDetails getUserById(UUID userId) {
        log.info("Invoking getUserById with userId={}",userId);
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if(userEntity.isEmpty()) {
            throw new UserNotFoundException(userId);
        }
        return new UserDetails(
                userEntity.get().getId(),
                userEntity.get().getName(),
                userEntity.get().getSurname(),
                userEntity.get().getEmail(),
                userEntity.get().getBirth_date(),
                userEntity.get().getPhone_number()
        );
    }

    private List<UserFromTrip> getUsersFromTrip(Integer tripId) {
        log.info("Invoking getUsersFromTrip with tripId={}",tripId);
        Optional<List<UserTripEntity>> userTripEntity = userTripRepository.findByTripId(tripId);
        if(userTripEntity.isEmpty()) {
            log.warn("Cannot find trip with tripId={}",tripId);
            throw new TripNotFoundException(tripId);
        }
        return userTripEntity.get().stream()
                .map(userTrip -> new UserFromTrip(
                        userTrip.getUser().getId(),
                        new Role(userTrip.getRole().getName())
                ))
                .collect(Collectors.toList());
    }

    public User getDriverFromTrip(Integer tripId) {
        log.info("Invoking getDriverFromTrip with tripId={}",tripId);
        List<UserFromTrip> users = getUsersFromTrip(tripId);
        UserDetails driver = users.stream()
                .filter(user -> user.role().name().equals("Driver"))
                .map(user -> getUserById(user.userId()))
                .findFirst()
                .orElseThrow(() -> new TripNotFoundException(tripId));
        return new User(
                driver.id(),
                driver.name(),
                driver.surname(),
                driver.email()
        );
    }

    public void deleteUser(UUID userId) {
        log.info("Invoking deleteUser with userId={}",userId);
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if(userEntity.isEmpty()) {
            log.warn("Cannot find user with userId={}",userId);
            throw new UserNotFoundException(userId);
        }
        userRepository.delete(userEntity.get());
    }
}
