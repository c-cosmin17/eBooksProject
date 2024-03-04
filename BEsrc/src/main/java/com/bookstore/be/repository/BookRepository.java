package com.bookstore.be.repository;

import com.bookstore.be.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
    Book findByTitle(String title);

    List<Book> findBooksByIdIn(List<Integer> ids);

}
