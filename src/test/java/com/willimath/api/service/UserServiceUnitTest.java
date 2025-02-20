package com.willimath.api.service;

import com.willimath.api.model.User;
import com.willimath.api.data.UserEntity;
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
        Integer id = 1;
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(UserEntityList.ALICE));

        // When
        UserDetails retrievedUser = userService.getUserById(id);

        // Then
        Assertions.assertThat(retrievedUser.email()).isEqualTo(UserEntityList.ALICE.getEmail());
    }
}