package com.bookstore.be.service;



import com.bookstore.be.model.Publisher;
import com.bookstore.be.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    @Autowired
    private PublisherRepository repository;

    public Publisher savePublisher(Publisher publisher){
        return repository.save(publisher);
    }

    public List<Publisher> savePublishers(List<Publisher> publishers){
        return repository.saveAll(publishers);
    }

    public List<Publisher> getPublishers(){
        return repository.findAll();
    }

    public Publisher getPublisherById(int id){
        return repository.findById(id).orElse(null);
    }

    public Publisher getPublisherByName(String name){
        return repository.findByName(name);
    }

    public String deletePublisherById(int id){
        repository.deleteById(id);
        return "Publisher removed: " + id;
    }

    public Publisher updatePublisher(Publisher publisher){
        Publisher existingPublisher = repository.findById(publisher.getId()).orElse(null);
        if (existingPublisher != null){
            existingPublisher.setName(publisher.getName());
            return repository.save(existingPublisher);
        } else
            return repository.save(publisher);
    }
}
