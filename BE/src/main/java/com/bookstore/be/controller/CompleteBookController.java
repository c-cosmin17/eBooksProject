package com.bookstore.be.controller;

import com.bookstore.be.model.Book;
import com.bookstore.be.model.BookComplete;
import com.bookstore.be.model.PurchasedBooks;
import com.bookstore.be.service.BookService;
import com.bookstore.be.service.CompleteBookService;
import com.bookstore.be.service.PurchasedBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CompleteBookController {
    @Autowired
    private CompleteBookService service;
    @Autowired
    private PurchasedBooksService purchasedBooksService;


    //endpoint pentru interogarea tuturor cartilor din baza de date
    @GetMapping("/Books")
    public List<BookComplete> getBooks(){
        return service.getBooks();
    }


    //endpoint pentru interogarea tuturor cartilor unui utilizator din cosul de cumparaturi
    @GetMapping("/shoppingCart/{userId}")
    public List<BookComplete> getBooks(@PathVariable int userId){
        return service.getBooksById(userId);
    }


    //adauga cartile detinute de fiecare utilizator in baza de date
    @PostMapping("/addPurchasedBooks")
    public List<PurchasedBooks> addPurchasedBooks(@RequestBody List<PurchasedBooks> books){
        return purchasedBooksService.savePurchasedBooks(books);
    }

    //extrage cartile detinute de un utilizator din baza de date
    @GetMapping("/purchasedBooks/{userId}")
    public List<BookComplete> getPurchasedBooks(@PathVariable int userId){
        return service.getPurchasedBooksById(userId);
    }
}
