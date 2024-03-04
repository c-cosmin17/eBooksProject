package com.bookstore.be.service;

import com.bookstore.be.model.PurchasedBooks;
import com.bookstore.be.repository.PurchasedBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchasedBooksService {
    @Autowired
    private PurchasedBooksRepository repository;

    public List<PurchasedBooks> savePurchasedBooks(List<PurchasedBooks> purchasedBooks){
        return repository.saveAll(purchasedBooks);
    }
}
