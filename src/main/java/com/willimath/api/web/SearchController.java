package com.willimath.api.web;

import com.willimath.api.model.Trip;
import com.willimath.api.model.User;
import com.willimath.api.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Search", description = "The Search API")
@RestController
@RequestMapping("/search")
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @Operation(summary = "Returns a user by name", description = "Returns a user by name", security = {@SecurityRequirement(name = "bearerAuth")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns a user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class))),

    })
    @GetMapping("/user")
    public User getUserByName(@RequestParam("name") String name) {
        return searchService.getUserByName(name);
    }

    @Operation(summary = "Returns a trip", description = "Returns a trip", security = {@SecurityRequirement(name = "bearerAuth")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns a trip",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Trip.class))}),
            @ApiResponse(responseCode = "404", description = "Trip not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)))

    })
    @GetMapping("/trip")
    public List<Trip> getTrajet(@RequestParam("from") String villedepart, @RequestParam("to") String villearrivee, @RequestParam("date") String date) {
        return searchService.getTrajet(villedepart, villearrivee, date);
    }

}
