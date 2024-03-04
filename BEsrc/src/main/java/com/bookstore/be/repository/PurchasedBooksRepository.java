package com.bookstore.be.repository;

import com.bookstore.be.model.PurchasedBooks;
import com.bookstore.be.model.ShoppingCart;
import com.bookstore.be.model.ShoppingCartId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchasedBooksRepository extends JpaRepository<PurchasedBooks, ShoppingCartId> {
    @Transactional
    @Modifying
    @Query("SELECT DISTINCT e.bookId FROM PurchasedBooks e WHERE e.customerId = :value")
    List<Integer> getBooksByCustomerId(@Param("value") int value);
}
