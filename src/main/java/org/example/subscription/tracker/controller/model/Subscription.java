package org.example.subscription.tracker.controller.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
public class Subscription {
    @Setter
    private Long id;
    private Long userId;
    private String serviceName;
    @Setter
    private Instant startDate;
    private double price;

    public Subscription(Long id, Long userId, String serviceName, Instant startDate, double price) {
        this.id = id;
        this.userId = userId;
        this.serviceName = serviceName;
        this.startDate = startDate;
        this.price = price;
    }

}
