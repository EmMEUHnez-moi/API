package com.willimath.api.web;

import com.willimath.api.Trip;
import com.willimath.api.service.TripService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Trajet", description = "The Trajet API")
@RestController
@RequestMapping("/trajet")
public class TripController {

    @Autowired
    private TripService tripService;

    @GetMapping
    public List<Trip> getTrajet(@RequestParam("from") String villedepart, @RequestParam("to") String villearrivee, @RequestParam("date") String date) {
        return tripService.getTrajet(villedepart, villearrivee, date);
    }

    @PostMapping
    public Trip createTrajet(@RequestBody Trip trip) {
        return tripService.createTrajet(trip);
    }

    @GetMapping("/{user_id}")
    public List<Trip> getTrajetByUser(@RequestParam("user_id") Integer user) {
        return tripService.getTrajetByUser(user);
    }
}
