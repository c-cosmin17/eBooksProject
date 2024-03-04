package com.bookstore.be.repository;


import com.bookstore.be.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription,Integer> {
    Subscription findByName(String name);
}
