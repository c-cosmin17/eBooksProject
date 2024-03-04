package com.bookstore.be.controller;

import com.bookstore.be.model.Book;
import com.bookstore.be.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {
    @Autowired
    private BookService service;

//    @PostMapping("/addBook")
//    public Book addBook(@RequestBody Book book){
//        return service.saveBook(book);
//    }
//
    @PostMapping("/addBooks")
    public List<Book> addBooks(@RequestBody List<Book> books){
        return service.saveBooks(books);
    }

    @GetMapping("/books")
    public List<Book> findAllBooks() {
        return service.getBooks();
    }

    @GetMapping("/book/{id}")
    public Book findBookById(@PathVariable int id) {
        return service.getBookById(id);
    }

//    @GetMapping("/bookByTitle/{title}")
//    public Book findProductByName(@PathVariable String title) {
//        return service.getBookByTitle(title);
//    }
//
//    @PutMapping("/updateBook")
//    public Book updateBook(@RequestBody Book book) {
//        return service.updateBook(book);
//    }
//
    @DeleteMapping("/books/delete")
    public String deleteBook() {
        return service.deleteBooks();
    }
}
