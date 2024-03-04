package com.bookstore.be.controller;


import com.bookstore.be.model.Subscriber;
import com.bookstore.be.model.Subscription;
import com.bookstore.be.service.SubscriberService;
import com.bookstore.be.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SubscriptionController {
    @Autowired
    private SubscriptionService service;

    @Autowired
    private SubscriberService subscriberService;

    @PostMapping("/addSubscriber")
    public Subscriber addSubscriber(@RequestBody Subscriber subscriber){
        return subscriberService.addSubscriber(subscriber);
    }

    @PostMapping("/addSubscription")
    public Subscription addSubscription(@RequestBody Subscription subscription){
        return service.saveSubscription(subscription);
    }

    @PostMapping("/addSubscriptions")
    public List<Subscription> addSubscriptions(@RequestBody List<Subscription> subscriptions){
        return service.saveSubscriptions(subscriptions);
    }

    @GetMapping("/subscriptions")
    public List<Subscription>  findAllSubscriptions() {
        return service.getSubscriptions();
    }

    @GetMapping("/subscriptionById/{id}")
    public Subscription findSubscriptionById(@PathVariable int id) {
        return service.getSubscriptionById(id);
    }

    @GetMapping("/subscriptionByName/{name}")
    public Subscription findSubscriptionByName(@PathVariable String name) {
        return service.getSubscriptionByName(name);
    }

    @PutMapping("/updateSubscription")
    public Subscription updateSubscription(@RequestBody Subscription subscription) {
        return service.updateSubscription(subscription);
    }

    @DeleteMapping("/deleteSubscription/{id}")
    public String deleteSubscription(@PathVariable int id) {
        return service.deleteSubscriptionById(id);
    }


}
