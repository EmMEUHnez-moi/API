package com.willimath.api.web;

import com.willimath.api.model.User;
import com.willimath.api.model.UserToSave;
import com.willimath.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "user", description = "the user")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Returns a user", description = "Returns a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns a user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)))

    })
    @GetMapping
    public List<User> getAllUser() {
        return userService.getAllUsers();
    }

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

    @Operation(summary = "Returns a user", description = "Returns a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns a user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "409", description = "User already exists",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class))),

    })
    @GetMapping("/{user_id}")
    public User getUser(@PathVariable("user_id") Integer user_id) {
        return userService.getUserById(user_id);
    }
}
