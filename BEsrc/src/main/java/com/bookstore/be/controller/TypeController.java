package com.bookstore.be.controller;


import com.bookstore.be.model.Type;
import com.bookstore.be.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TypeController {
    @Autowired
    private TypeService service;

    @PostMapping("/addType")
    public Type addType(@RequestBody Type type){
        return service.saveType(type);
    }

    @PostMapping("/addTypes")
    public List<Type> addTypes(@RequestBody List<Type> types){
        return service.saveTypes(types);
    }

    @GetMapping("/types")
    public List<Type>  findAllTypes() {
        return service.getTypes();
    }

    @GetMapping("/typeById/{id}")
    public Type findTypeById(@PathVariable int id) {
        return service.getTypeById(id);
    }

    @GetMapping("/typeByName/{name}")
    public Type findTypeByName(@PathVariable String name) {
        return service.getTypeByName(name);
    }

    @PutMapping("/updateType")
    public Type updateType(@RequestBody Type type) {
        return service.updateType(type);
    }

    @DeleteMapping("/deleteType/{id}")
    public String deleteType(@PathVariable int id) {
        return service.deleteTypeById(id);
    }
}
