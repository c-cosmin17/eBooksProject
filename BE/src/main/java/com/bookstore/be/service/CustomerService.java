package com.bookstore.be.service;

import com.bookstore.be.model.Customer;
import com.bookstore.be.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository repository;

    public Customer saveUser(Customer user){
        return repository.save(user);
    }

    public List<Customer> saveUsers(List<Customer> users){
        return repository.saveAll(users);
    }

    public List<Customer> getUsers(){
        return repository.findAll();
    }

    public Customer getUserById(int id){
        return repository.findById(id).orElse(null);
    }

    public Customer getUserByFirsName(String firstName){
        return repository.findByFirstName(firstName);
    }

    public String deleteUserById(int id){
        repository.deleteById(id);
        return "User removed: " + id;
    }

    public Customer updateCustomer(Customer user){
        Customer existingUser = repository.findById(user.getId()).orElse(null);
        if (existingUser != null){
            existingUser.setCardId(user.getCardId());
            existingUser.setEmail(user.getEmail());
            existingUser.setPhone(user.getPhone());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setCardNumber(user.getCardNumber());
            existingUser.setSubscriptionId(user.getSubscriptionId());
            return repository.save(existingUser);
        } else
            return repository.save(user);
    }
}
