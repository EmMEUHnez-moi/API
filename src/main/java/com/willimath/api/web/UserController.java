package com.willimath.api.web;

import com.willimath.api.Trip;
import com.willimath.api.User;
import com.willimath.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
                            array =  @ArraySchema(schema = @Schema(implementation = User.class)))})

    })
    @GetMapping
    public List<User> getAllUser() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Create a user", description = "Create a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create a user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))})

    })
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}
