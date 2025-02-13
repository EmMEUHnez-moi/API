package com.willimath.api.service;

import com.willimath.api.model.Trip;
import com.willimath.api.data.TripRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class TripServiceUnitTest {

    @Mock
    private TripRepository tripRepository;

    private TripService tripService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.tripService = new TripService(tripRepository);
    }

    @Test
    public void ShouldReturnAllTrips() {
        //Given
        Mockito.when(tripRepository.findAll()).thenReturn(null);
        //When
        List<Trip> trips = tripService.getAllTrajets();
        //Then
        Assertions.assertThat(trips).isNull();
    }

}
