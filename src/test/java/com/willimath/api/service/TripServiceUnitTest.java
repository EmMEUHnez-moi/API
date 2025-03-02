package com.willimath.api.service;

import com.willimath.api.data.TripRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TripServiceUnitTest {

    @Mock
    private TripRepository tripRepository;

    private TripService tripService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.tripService = new TripService(tripRepository);
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
        Mockito.when(tripRepository.findById(id)).thenReturn(Optional.of(UserEntityList.ALICE));

        // When
        List<Trip> retrievedTrip = tripService.getTrajetByUser(id);

        // Then
        Assertions.assertThat(retrievedTrip).extracting(people).containsExactly();
    }

 */

}
