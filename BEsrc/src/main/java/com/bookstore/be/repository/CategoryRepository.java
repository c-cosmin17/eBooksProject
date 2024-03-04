package com.bookstore.be.repository;

import com.bookstore.be.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Category findByName(String name);
}
