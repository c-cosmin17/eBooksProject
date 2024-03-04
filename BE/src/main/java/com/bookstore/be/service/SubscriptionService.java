package com.bookstore.be.service;

import com.bookstore.be.model.Subscription;
import com.bookstore.be.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionRepository repository;

    public Subscription saveSubscription(Subscription subscription){
        return repository.save(subscription);
    }

    public List<Subscription> saveSubscriptions(List<Subscription> subscriptions){
        return repository.saveAll(subscriptions);
    }

    public List<Subscription> getSubscriptions(){
        return repository.findAll();
    }

    public Subscription getSubscriptionById(int id){
        return repository.findById(id).orElse(null);
    }

    public Subscription getSubscriptionByName(String name){
        return repository.findByName(name);
    }

    public String deleteSubscriptionById(int id){
        repository.deleteById(id);
        return "Subscription removed: " + id;
    }

    public Subscription updateSubscription(Subscription subscription){
        Subscription existingSubscription = repository.findById(subscription.getId()).orElse(null);
        if (existingSubscription != null){
            existingSubscription.setName(subscription.getName());
            return repository.save(existingSubscription);
        } else
            return repository.save(subscription);
    }
}
