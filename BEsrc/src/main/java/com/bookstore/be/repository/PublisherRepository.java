package com.bookstore.be.repository;


import com.bookstore.be.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher,Integer> {
    Publisher findByName(String name);
}
