package com.bookstore.be.model;

import java.io.Serializable;

public class ShoppingCartId implements Serializable {
    private int customerId;
    private int bookId;

    public ShoppingCartId(int customerId, int bookId) {
        this.customerId = customerId;
        this.bookId = bookId;
    }

    public ShoppingCartId() {
    }


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
