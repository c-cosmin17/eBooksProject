package com.bookstore.be.service;

import com.bookstore.be.model.Subscriber;
import com.bookstore.be.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriberService {
    @Autowired
    SubscriberRepository subscriberRepository;

    public Subscriber addSubscriber(Subscriber subscriber) {
        Subscriber subscriber1 = subscriberRepository.findByEmail(subscriber.getEmail()).orElse(null);
        if (subscriber1 == null)
            return subscriberRepository.save(subscriber);
        return null;
    }
}
