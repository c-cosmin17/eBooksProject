package com.bookstore.be.repository;

import com.bookstore.be.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriberRepository extends JpaRepository<Subscriber,Integer> {
    public Optional<Subscriber> findByEmail(String email);
}
