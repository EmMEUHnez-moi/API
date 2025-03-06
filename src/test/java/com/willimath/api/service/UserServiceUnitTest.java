package com.willimath.api.service;

import com.willimath.api.data.UserEntityList;
import com.willimath.api.data.UserRepository;
import com.willimath.api.model.UserDetails;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static java.util.UUID.fromString;

public class UserServiceUnitTest {

    private UserService userService;
    @Mock
    private UserRepository userRepository;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void shouldReturnUser(){
        // Given
        UUID id = fromString("3721a1db-14ec-49f1-8f84-fc408b0f0918");
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(UserEntityList.ALICE));

        // When
        UserDetails retrievedUser = userService.getUserById(id);

        // Then
        Assertions.assertThat(retrievedUser.email()).isEqualTo(UserEntityList.ALICE.getEmail());
    }
}