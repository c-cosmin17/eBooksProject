package com.bookstore.be.service;

import com.bookstore.be.model.*;
import com.bookstore.be.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class CompleteBookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private PurchasedBooksRepository purchasedBooksRepository;
    public List<BookComplete> getBooks() {
        List<Book> books = bookRepository.findAll();
        List<Type> types = typeRepository.findAll();
        List<Category> categories = categoryRepository.findAll();
        List<Publisher> publishers = publisherRepository.findAll();
        // Now, let's map the data to BookComplete objects
        List<BookComplete> bookCompletes = new ArrayList<>();

        for (Book book : books) {
            BookComplete bookComplete = new BookComplete();
            bookComplete.setId(book.getId());
            bookComplete.setTitle(book.getTitle());
            bookComplete.setAuthor(book.getAuthor());
            bookComplete.setPrice(book.getPrice());
            bookComplete.setAuthorDescription(book.getAuthorDescription());
            bookComplete.setBookDescription(book.getBookDescription());
            bookComplete.setImagePath(book.getImagePath());
            // Find and set category
            Category category = findCategoryById(categories, book.getCategoryId());
            if (category != null) {
                bookComplete.setCategory(category.getName());
            }

            // Find and set type
            Type type = findTypeById(types, book.getTypeId());
            if (type != null) {
                bookComplete.setType(type.getName());
            }

            Publisher publisher = findPublisherById(publishers, book.getPublisherId());
            if (publisher != null) {
                bookComplete.setPublisher(publisher.getName());
            }

            // Add the BookComplete object to the list
            bookCompletes.add(bookComplete);
        }

        return bookCompletes;
    }

    public List<BookComplete> getBooksById(int userId) {
        List<Integer> bookIds = shoppingCartRepository.getBooksByCustomerId(userId);
        List<Book> books = bookRepository.findBooksByIdIn(bookIds);
        List<Type> types = typeRepository.findAll();
        List<Category> categories = categoryRepository.findAll();
        List<Publisher> publishers = publisherRepository.findAll();
        // Now, let's map the data to BookComplete objects
        List<BookComplete> bookCompletes = new ArrayList<>();

        for (Book book : books) {
            BookComplete bookComplete = new BookComplete();
            bookComplete.setId(book.getId());
            bookComplete.setTitle(book.getTitle());
            bookComplete.setAuthor(book.getAuthor());
            bookComplete.setPrice(book.getPrice());
            bookComplete.setAuthorDescription(book.getAuthorDescription());
            bookComplete.setBookDescription(book.getBookDescription());
            bookComplete.setImagePath(book.getImagePath());
            // Find and set category
            Category category = findCategoryById(categories, book.getCategoryId());
            if (category != null) {
                bookComplete.setCategory(category.getName());
            }

            // Find and set type
            Type type = findTypeById(types, book.getTypeId());
            if (type != null) {
                bookComplete.setType(type.getName());
            }

            Publisher publisher = findPublisherById(publishers, book.getPublisherId());
            if (publisher != null) {
                bookComplete.setPublisher(publisher.getName());
            }

            // Add the BookComplete object to the list
            bookCompletes.add(bookComplete);
        }

        return bookCompletes;
    }

    public List<BookComplete> getPurchasedBooksById(int userId) {
        List<Integer> bookIds = purchasedBooksRepository.getBooksByCustomerId(userId);
        List<Book> books = bookRepository.findBooksByIdIn(bookIds);
        List<Type> types = typeRepository.findAll();
        List<Category> categories = categoryRepository.findAll();
        List<Publisher> publishers = publisherRepository.findAll();
        List<BookComplete> bookCompletes = new ArrayList<>();

        for (Book book : books) {
            BookComplete bookComplete = new BookComplete();
            bookComplete.setId(book.getId());
            bookComplete.setTitle(book.getTitle());
            bookComplete.setAuthor(book.getAuthor());
            bookComplete.setPrice(book.getPrice());
            bookComplete.setAuthorDescription(book.getAuthorDescription());
            bookComplete.setBookDescription(book.getBookDescription());
            bookComplete.setImagePath(book.getImagePath());
            bookComplete.setPdfPath(book.getPdfPath());

            Category category = findCategoryById(categories, book.getCategoryId());
            if (category != null) {
                bookComplete.setCategory(category.getName());
            }


            Type type = findTypeById(types, book.getTypeId());
            if (type != null) {
                bookComplete.setType(type.getName());
            }

            Publisher publisher = findPublisherById(publishers, book.getPublisherId());
            if (publisher != null) {
                bookComplete.setPublisher(publisher.getName());
            }

            bookCompletes.add(bookComplete);
        }

        return bookCompletes;
    }

    private Category findCategoryById(List<Category> categories, int categoryId) {
        return categories.stream()
                .filter(category -> category.getId() == categoryId)
                .findFirst()
                .orElse(null);
    }

    private Publisher findPublisherById(List<Publisher> publishers, int publisherId) {
        return publishers.stream()
                .filter(category -> category.getId() == publisherId)
                .findFirst()
                .orElse(null);
    }

    private Type findTypeById(List<Type> types, int typeId) {
        return types.stream()
                .filter(type -> type.getId() == typeId)
                .findFirst()
                .orElse(null);
    }
}
