package com.bookstore.be.controller;


import com.bookstore.be.model.Publisher;
import com.bookstore.be.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PublisherController {
    @Autowired
    private PublisherService service;

    @PostMapping("/addPublisher")
    public Publisher addPublisher(@RequestBody Publisher publisher){
        return service.savePublisher(publisher);
    }

    @PostMapping("/addPublishers")
    public List<Publisher> addPublishers(@RequestBody List<Publisher> publishers){
        return service.savePublishers(publishers);
    }

    @GetMapping("/publishers")
    public List<Publisher>  findAllPublishers() {
        return service.getPublishers();
    }

    @GetMapping("/publisherById/{id}")
    public Publisher findPublisherById(@PathVariable int id) {
        return service.getPublisherById(id);
    }

    @GetMapping("/publisherByName/{name}")
    public Publisher findPublisherByName(@PathVariable String name) {
        return service.getPublisherByName(name);
    }

    @PutMapping("/updatePublisher")
    public Publisher updatePublisher(@RequestBody Publisher publisher) {
        return service.updatePublisher(publisher);
    }

    @DeleteMapping("/deletePublisher/{id}")
    public String deletePublisher(@PathVariable int id) {
        return service.deletePublisherById(id);
    }
}
