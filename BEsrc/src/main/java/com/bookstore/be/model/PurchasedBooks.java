package com.bookstore.be.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "purchasedBooks")
@IdClass(ShoppingCartId.class)
public class PurchasedBooks {
    @Id
    private int customerId;
    @Id
    private int bookId;
}