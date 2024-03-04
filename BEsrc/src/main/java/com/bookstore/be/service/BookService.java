package com.bookstore.be.service;

import com.bookstore.be.model.Book;
import com.bookstore.be.model.UpdateBook;
import com.bookstore.be.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository repository;
    //Adauga o carte in baza de date
    public Book saveBook(Book book){
        return repository.save(book);
    }
    //Adauga o lista de carti in baza de date
    public List<Book> saveBooks(List<Book> books){
        return repository.saveAll(books);
    }
    //Extrage toate cartile din baza de date
    public List<Book> getBooks(){
        return repository.findAll();
    }
    //Extrage cartile cu care au un anumit id din baza de date
    public List<Book> getBooksById(List<Integer> ids){
        return repository.findBooksByIdIn(ids);
    }
    //Extrage cartea cu id din baza de date
    public Book getBookById(int id){
        return repository.findById(id).orElse(null);
    }
    //cautare dupa titlu
    public Book getBookByTitle(String title){
        return repository.findByTitle(title);
    }
    //sterge cartea din baza de date
    public String deleteBookById(int id){
        repository.deleteById(id);
        return "Book removed: " + id;
    }
    //sterge cartile din baza de date
    public String deleteBooks(){
        repository.deleteAll();
        return "Books removed: ";
    }
    //actualizeaza o carte in baza de date
    public Book updateBook(UpdateBook book, int id){
        Book existingBook = repository.findById(id).orElse(null);
        if (existingBook != null){
            existingBook.setCategoryId(book.getCategoryId());
            existingBook.setPublisherId(book.getPublisherId());
            existingBook.setTypeId(book.getTypeId());
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setPrice(book.getPrice());
            return repository.save(existingBook);
        } else return null;
    }


}
