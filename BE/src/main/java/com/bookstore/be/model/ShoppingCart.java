package com.bookstore.be.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shoppingcarts")
@IdClass(ShoppingCartId.class)
public class ShoppingCart {
    @Id
    private int customerId;
    @Id
    private int bookId;

}
