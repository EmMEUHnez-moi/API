package com.willimath.api.service;

import com.willimath.api.User;
import com.willimath.api.data.UserEntity;
import com.willimath.api.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userEntity -> new User(
                        userEntity.getName(),
                        userEntity.getSurname(),
                        userEntity.getEmail(),
                        userEntity.getPassword()
                ))
                .collect(Collectors.toList());
    }

    public User createUser(User user) {
        userRepository.save(new UserEntity(
            user.name(),
            user.surname(),
            user.email(),
            user.password()
        ));
        return user;
    }
}
