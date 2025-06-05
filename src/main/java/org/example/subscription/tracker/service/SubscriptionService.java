package org.example.subscription.tracker.service;

import org.example.subscription.tracker.controller.model.Subscription;
import org.example.subscription.tracker.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.Instant;
import java.util.List;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public Subscription addSubscription(Subscription subscription) throws SQLException {
        subscription.setStartDate(Instant.now());
        return subscriptionRepository.addSubscription(subscription);
    }

    public List<Subscription> getSubscriptionsByUserId(Long userId) throws SQLException {
        return subscriptionRepository.getSubscriptionsByUserId(userId);
    }

    public void deleteSubscription(Long subId) throws SQLException {
        subscriptionRepository.deleteSubscription(subId);
    }

    public List<String> getTop3PopularSubscriptions() throws SQLException {
        return subscriptionRepository.getTop3PopularSubscriptions();
    }
}
