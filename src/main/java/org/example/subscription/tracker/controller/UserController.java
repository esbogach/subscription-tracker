package org.example.subscription.tracker.controller;

import org.example.subscription.tracker.controller.model.User;
import org.example.subscription.tracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) throws SQLException {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<User> getUser(@PathVariable("user_id") Long userId) throws SQLException {
        User user = userService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping
    public ResponseEntity<Void> updateUser(@RequestBody User user) throws SQLException {
        userService.updateUser(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("user_id") Long userId) throws SQLException {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
