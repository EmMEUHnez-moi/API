package com.willimath.api.web;

import com.willimath.api.model.Trip;
import com.willimath.api.model.User;
import com.willimath.api.service.TripService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Trajet", description = "The Trajet API")
@RestController
@RequestMapping("/trajet")
public class TripController {

    @Autowired
    private TripService tripService;

    @Operation(summary = "Returns a trip", description = "Returns a trip")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns a trip",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "Trip not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)))

    })
    @GetMapping
    public List<Trip> getTrajet(@RequestParam("from") String villedepart, @RequestParam("to") String villearrivee, @RequestParam("date") String date) {
        return tripService.getTrajet(villedepart, villearrivee, date);
    }

    @Operation(summary = "Create a trip", description = "Create a trip")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create a trip",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "409", description = "Trip already exists",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)))

    })
    @PostMapping
    public Trip createTrajet(@RequestBody Trip trip) {
        return tripService.createTrajet(trip);
    }

    @Operation(summary = "Returns a trip by user id", description = "Returns a trip by user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns a trip by user id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "Trip not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)))

    })
    @GetMapping("/{user_id}")
    public List<Trip> getTrajetByUser(@PathVariable("user_id") Integer user) {
        return tripService.getTrajetByUser(user);
    }
}
