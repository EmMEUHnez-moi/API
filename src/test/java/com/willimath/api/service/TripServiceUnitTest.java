package com.willimath.api.service;

import com.willimath.api.data.*;
import com.willimath.api.model.Trip;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class TripServiceUnitTest {

    @Mock
    private TripRepository tripRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserTripRepository userTripRepository;

    private TripService tripService;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.userService = new UserService(userRepository, userTripRepository);
        this.tripService = new TripService(tripRepository, userService);
    }
    /*
    @Test
    public void ShouldReturnAllTrips() {
        //Given
        Mockito.when(tripRepository.findAll()).thenReturn(null);
        //When
        List<Trip> trips = tripService.getAllTrajets();
        //Then
        Assertions.assertThat(trips).isNull();
    }


    @Test
    public void shouldReturnTrip(){

        // Given
        Integer id = 1;
        Mockito.when(tripRepository.findById(id)).thenReturn((Optional.of(TripEntityList.PARIS)));
        Mockito.when(userTripRepository.findByTripId(id)).thenReturn((Optional.of(UserTripEntityList.list)));
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(UserEntityList.ALICE));

        // When
        Trip retrievedTrip = tripService.getTrajetById(id);

        // Then
        Assertions.assertThat(retrievedTrip.to_location()).isEqualTo("Lyon");
    }


*/
}
