package org.example.subscription.tracker.repository;

import lombok.RequiredArgsConstructor;
import org.example.subscription.tracker.controller.model.Subscription;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SubscriptionRepository {

    private final DataSource dataSource;


    public Subscription addSubscription(Subscription subscription) throws SQLException {
        String sql = "INSERT INTO subscriptions (user_id, service_name, start_date, price) VALUES (?, ?, ?, ?) RETURNING id";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, subscription.getUserId());
            stmt.setString(2, subscription.getServiceName());
            stmt.setLong(3, subscription.getStartDate().getEpochSecond());
            stmt.setDouble(4, subscription.getPrice());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                subscription.setId(rs.getLong("id"));
            }
        }
        return subscription;
    }

    public List<Subscription> getSubscriptionsByUserId(Long userId) throws SQLException {
        List<Subscription> subscriptions = new ArrayList<>();
        String sql = "SELECT * FROM subscriptions WHERE user_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                subscriptions.add(new Subscription(
                    rs.getLong("id"),
                    rs.getLong("user_id"),
                    rs.getString("service_name"),
                    Instant.ofEpochSecond(rs.getLong("start_date")),
                    rs.getDouble("price")
                ));
            }
        }
        return subscriptions;
    }

    public void deleteSubscription(Long subId) throws SQLException {
        String sql = "DELETE FROM subscriptions WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, subId);
            stmt.executeUpdate();
        }
    }

    public List<String> getTop3PopularSubscriptions() throws SQLException {
        List<String> topServices = new ArrayList<>();
        String sql = "SELECT service_name, COUNT(*) as count FROM subscriptions GROUP BY service_name ORDER BY count DESC LIMIT 3";
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                topServices.add(rs.getString("service_name"));
            }
        }
        return topServices;
    }


}
