package com.bookstore.be.controller;

import com.bookstore.be.model.Category;
import com.bookstore.be.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @PostMapping("/addCategory")
    public Category addCategory(@RequestBody Category category){
        return service.saveCategory(category);
    }

    @PostMapping("/addCategories")
    public List<Category> addCategories(@RequestBody List<Category> categories){
        return service.saveCategories(categories);
    }

    @GetMapping("/categories")
    public List<Category>  findAllCategories() {
        return service.getCategories();
    }

    @GetMapping("/categoryById/{id}")
    public Category findCategoryById(@PathVariable int id) {
        return service.getCategoryById(id);
    }

    @GetMapping("/categoryByName/{name}")
    public Category findCategoryByName(@PathVariable String name) {
        return service.getCategoryByName(name);
    }

    @PutMapping("/updateCategory")
    public Category updateCategory(@RequestBody Category category) {
        return service.updateCategory(category);
    }

    @DeleteMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable int id) {
        return service.deleteCategoryById(id);
    }
}
