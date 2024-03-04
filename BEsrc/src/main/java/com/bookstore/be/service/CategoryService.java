package com.bookstore.be.service;

import com.bookstore.be.model.Category;
import com.bookstore.be.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public Category saveCategory(Category category){
        return repository.save(category);
    }

    public List<Category> saveCategories(List<Category> categories){
        return repository.saveAll(categories);
    }

    public List<Category> getCategories(){
        return repository.findAll();
    }

    public Category getCategoryById(int id){
        return repository.findById(id).orElse(null);
    }

    public Category getCategoryByName(String name){
        return repository.findByName(name);
    }

    public String deleteCategoryById(int id){
        repository.deleteById(id);
        return "Category removed: " + id;
    }

    public Category updateCategory(Category category){
        Category existingCategory = repository.findById(category.getId()).orElse(null);
        if (existingCategory != null){
            existingCategory.setName(category.getName());
            return repository.save(existingCategory);
        } else
            return repository.save(category);
    }
}
