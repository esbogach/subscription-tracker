package org.example.subscription.tracker.controller;

import org.example.subscription.tracker.controller.model.Subscription;
import org.example.subscription.tracker.service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public ResponseEntity<Subscription> addSubscription(@RequestBody Subscription subscription) throws SQLException {
        Subscription createdSubscription = subscriptionService.addSubscription(subscription);
        return ResponseEntity.ok(createdSubscription);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<List<Subscription>> getSubscriptions(@PathVariable("user_id") Long userId) throws SQLException {
        List<Subscription> subscriptions = subscriptionService.getSubscriptionsByUserId(userId);
        return ResponseEntity.ok(subscriptions);
    }

    @DeleteMapping("/{sub_id}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable("sub_id") Long subId) throws SQLException {
        subscriptionService.deleteSubscription(subId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/top3")
    public ResponseEntity<List<String>> getTop3Subscriptions() throws SQLException {
        List<String> topServices = subscriptionService.getTop3PopularSubscriptions();
        return ResponseEntity.ok(topServices);
    }
}
