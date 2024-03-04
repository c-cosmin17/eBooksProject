package com.bookstore.be.service;


import com.bookstore.be.model.User;
import com.bookstore.be.model.Customer;
import com.bookstore.be.repository.UserRepository;
import com.bookstore.be.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;

    public void registerUser(Customer user, User login) {
        // Save user and credit card entities
        customerRepository.save(user);
        userRepository.save(login);
    }
}
