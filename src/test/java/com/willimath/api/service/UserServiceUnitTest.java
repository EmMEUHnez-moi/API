package com.willimath.api.service;

import com.willimath.api.model.User;
import com.willimath.api.data.UserEntity;
import com.willimath.api.data.UserRepository;
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

    @Mock
    private UserEntity userEntity;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.userService = new UserService(userRepository);
    }

    @Test
    public void ShouldReturnUser1() {
        //Given
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(userEntity));
        //When
        User retrivedUser = userService.getUserById(1);
        //Then
        Assertions.assertThat(retrivedUser.email()).isEqualTo("alice.dupont@example.com");
    }

}
