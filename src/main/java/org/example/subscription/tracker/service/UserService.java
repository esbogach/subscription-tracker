package org.example.subscription.tracker.service;

import org.example.subscription.tracker.controller.model.User;
import org.example.subscription.tracker.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.Instant;

@Service
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) throws SQLException {
        user.setCreatedAt(Instant.now());
        return userRepository.createUser(user);
    }

    public User getUserById(Long userId) throws SQLException {
        return userRepository.getUserById(userId);
    }

    public void updateUser(User user) throws SQLException {
        userRepository.updateUser(user);
    }

    public void deleteUser(Long userId) throws SQLException {
        userRepository.deleteUser(userId);
    }

}
