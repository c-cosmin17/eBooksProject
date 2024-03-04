package com.bookstore.be.controller;

import com.bookstore.be.model.Customer;
import com.bookstore.be.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {
    @Autowired
    private CustomerService service;

    @PostMapping("/addUser")
    public Customer addUser(@RequestBody Customer user){
        return service.saveUser(user);
    }

    @PostMapping("/addUsers")
    public List<Customer> addUsers(@RequestBody List<Customer> users){
        return service.saveUsers(users);
    }

    @GetMapping("/users")
    public List<Customer> findAllUsers() {
        return service.getUsers();
    }

    @GetMapping("/userById/{id}")
    public Customer findUserById(@PathVariable int id) {
        return service.getUserById(id);
    }

    @GetMapping("/userByName/{firstName}")
    public Customer findUserByName(@PathVariable String firstName) {
        return service.getUserByFirsName(firstName);
    }

    @PutMapping("/updateCustomer")
    public Customer updateCustomer(@RequestBody Customer customer) {
        return service.updateCustomer(customer);
    }

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable int id) {
        return service.deleteUserById(id);
    }
}
