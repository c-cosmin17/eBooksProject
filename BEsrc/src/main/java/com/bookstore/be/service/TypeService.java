package com.bookstore.be.service;

import com.bookstore.be.model.Category;
import com.bookstore.be.model.Type;
import com.bookstore.be.repository.CategoryRepository;
import com.bookstore.be.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {
    @Autowired
    private TypeRepository repository;

    public Type saveType(Type type){
        return repository.save(type);
    }

    public List<Type> saveTypes(List<Type> types){
        return repository.saveAll(types);
    }

    public List<Type> getTypes(){
        return repository.findAll();
    }

    public Type getTypeById(int id){
        return repository.findById(id).orElse(null);
    }

    public Type getTypeByName(String name){
        return repository.findByName(name);
    }

    public String deleteTypeById(int id){
        repository.deleteById(id);
        return "Type removed: " + id;
    }

    public Type updateType(Type type){
        Type existingType = repository.findById(type.getId()).orElse(null);
        if (existingType != null){
            existingType.setName(type.getName());
            return repository.save(existingType);
        } else
            return repository.save(type);
    }
}
