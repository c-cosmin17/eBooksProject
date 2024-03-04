package com.bookstore.be.repository;

import com.bookstore.be.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Integer> {
    Card findByName(String name);
}
