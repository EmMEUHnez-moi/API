package com.willimath.api.web;

import com.willimath.api.model.User;
import com.willimath.api.model.UserDetails;
import com.willimath.api.model.UserModifications;
import com.willimath.api.model.UserToSave;
import com.willimath.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@Tag(name = "User", description = "The User API")
@RestController
@RequestMapping("/user")
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Operation(summary = "Create a user", description = "Create a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create a user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserToSave.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class))),

    })
    @PostMapping
    public UserToSave createUser(@RequestBody @Valid UserToSave user) {
        return userService.createUser(user);
    }

    @Operation(summary = "Returns a user", description = "Returns a user", security = {@SecurityRequirement(name = "bearerAuth")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns a user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "409", description = "User already exists",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class))),

    })
    @GetMapping("/{user_id}")
    public UserDetails getUser(@PathVariable("user_id") UUID user_id) {
        log.info("Get user with id {}", user_id);
        return userService.getUserById(user_id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class))),

    })
    @Operation(summary = "Delete a user", description = "Delete a user", security = {@SecurityRequirement(name = "bearerAuth")})
    @DeleteMapping("/{user_id}")
    public void deleteUser(@PathVariable("user_id") UUID user_id) {
        userService.deleteUser(user_id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Change user details",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class))),

    })
    @Operation(summary = "Change a user details", description = "Change a user details", security = {@SecurityRequirement(name = "bearerAuth")})
    @PutMapping("/{user_id}")
    public void changeDetails (@PathVariable("user_id") UUID user_id, @RequestBody UserModifications userModifications ){

        userService.changeDetails(user_id, userModifications);
    }



}
