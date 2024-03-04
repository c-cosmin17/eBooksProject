package com.bookstore.be.repository;


import com.bookstore.be.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type,Integer> {
    Type findByName(String name);
}
