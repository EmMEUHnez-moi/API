package com.willimath.api.web;

import com.willimath.api.model.Trip;
import com.willimath.api.model.User;
import com.willimath.api.service.TripService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Trip", description = "The Trip API")
@RestController
@RequestMapping("/trip")
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
public class TripController {

    @Autowired
    private TripService tripService;

    @Operation(summary = "Create a trip", description = "Create a trip", security = {@SecurityRequirement(name = "bearerAuth")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create a trip",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Trip.class))}),
            @ApiResponse(responseCode = "409", description = "Trip already exists",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)))

    })
    @PostMapping
    public Trip createTrajet(@RequestBody @Valid Trip trip) {
        return tripService.createTrajet(trip);
    }

    @Operation(summary = "Returns a trip by id", description = "Returns a trip by id", security = {@SecurityRequirement(name = "bearerAuth")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns a trip by id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Trip.class))}),
            @ApiResponse(responseCode = "404", description = "Trip not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)))

    })
    @GetMapping("/{trip_id}")
    public Trip getTrajetByUser(@PathVariable("trip_id") Integer tripId) {
        return tripService.getTrajetById(tripId);
    }

    @Operation(summary = "Returns a list of passagers by trip id", description = "Returns a list of passagers by trip id", security = {@SecurityRequirement(name = "bearerAuth")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns a list of passagers by trip id",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = User.class)))}),
            @ApiResponse(responseCode = "404", description = "Trip not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)))

    })
    @GetMapping("/{trip_id}/passagers")
    public List<User> getPassagersByTrajet(Integer tripId) {
        return tripService.getPassagersByTrajet(tripId);
    }

    @Operation(summary = "Add a passager to a trip", description = "Add a passager to a trip", security = {@SecurityRequirement(name = "bearerAuth")})
    @PostMapping("/{trip_id}/passagers/{user_id}")
    public void addPassagerToTrajet(Integer tripId, UUID userId) {
        tripService.addPassagerToTrajet(tripId, userId);
    }
}
