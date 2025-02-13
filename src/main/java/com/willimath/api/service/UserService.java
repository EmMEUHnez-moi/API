package com.willimath.api.service;

import com.willimath.api.model.User;
import com.willimath.api.model.UserToSave;
import com.willimath.api.data.UserEntity;
import com.willimath.api.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userEntity -> new User(
                        userEntity.getId(),
                        userEntity.getName(),
                        userEntity.getSurname(),
                        userEntity.getEmail()
                ))
                .collect(Collectors.toList());
    }

    public UserToSave createUser(UserToSave user) {
        userRepository.save(new UserEntity(
            user.name(),
            user.surname(),
            user.email(),
            user.password()
        ));
        return user;
    }

    public User getUserById(Integer userId) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if(userEntity.isEmpty()) {
            throw new RuntimeException("No user found");
        }
        return new User(
                userEntity.get().getId(),
                userEntity.get().getName(),
                userEntity.get().getSurname(),
                userEntity.get().getEmail()
        );
    }
}
