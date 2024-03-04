package com.bookstore.be.controller;

import com.bookstore.be.model.*;
import com.bookstore.be.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ManagerController {
    @Autowired
    private BookService service;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TypeService typeService;

    @Autowired
    private PublisherService publisherService;
    @Autowired
    private ImageUtils imageUtils;
    @GetMapping("/manager/books")
    public List<Book> findAllBooks() {
        return service.getBooks();
    }

    @GetMapping("/manager/getBook/{id}")
    public Book findBookById(@PathVariable int id) {
        return service.getBookById(id);
    }

    @PostMapping(value = "/manager/addBook", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Book addBook(@RequestPart("book") Book book,
                        @RequestPart("bookCover") MultipartFile cover,
                        @RequestPart("pdfFile") MultipartFile pdf) throws IOException {
        // Save the image file and set the image path in the book entity
        String imagePath = imageUtils.saveImage(cover);
        String pdfPath = imageUtils.savePDF(pdf);
        book.setImagePath(imagePath);
        book.setPdfPath(pdfPath);
        return service.saveBook(book);
    }


    @PutMapping("/manager/updateBook/{id}")
    public Book updateBook(@RequestBody UpdateBook book, @PathVariable int id) {
        return service.updateBook(book, id);
    }



    @DeleteMapping("/manager/deleteBook/{id}")
    public String deleteBook(@PathVariable int id) {
        return service.deleteBookById(id);
    }


    @DeleteMapping("/manager/deleteCategory/{id}")
    public String deleteCategory(@PathVariable int id) {
        return categoryService.deleteCategoryById(id);
    }

    @PostMapping("/manager/addCategory")
    public Category addCategory(@RequestBody Category category){
        return categoryService.saveCategory(category);
    }


    @GetMapping("/manager/categories")
    public List<Category>  findAllCategories() {
        return categoryService.getCategories();
    }


    @DeleteMapping("/manager/deleteType/{id}")
    public String deleteType(@PathVariable int id) {
        return typeService.deleteTypeById(id);
    }

    @PostMapping("/manager/addType")
    public Type addType(@RequestBody Type type){
        return typeService.saveType(type);
    }

    @GetMapping("/manager/types")
    public List<Type>  findAllTypes() {
        return typeService.getTypes();
    }

    @DeleteMapping("/manager/deletePublisher/{id}")
    public String deletePublisher(@PathVariable int id) {
        return publisherService.deletePublisherById(id);
    }

    @PostMapping("/manager/addPublisher")
    public Publisher addPublisher(@RequestBody Publisher publisher){
        return publisherService.savePublisher(publisher);
    }

    @GetMapping("/manager/publishers")
    public List<Publisher>  findAllPublishers() {
        return publisherService.getPublishers();
    }
}
