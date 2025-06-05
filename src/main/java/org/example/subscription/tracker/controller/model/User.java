package org.example.subscription.tracker.controller.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
public class User {
    @Setter
    private Long id;
    private String name;
    private String email;
    @Setter
    private Instant createdAt;

    public User(Long id, String name, String email, Instant createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
    }
}
