package com.bookstore.be.controller;

import com.bookstore.be.model.User;
import com.bookstore.be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/addLogin")
    public User addLogin(@RequestBody User user){
        return service.saveLogin(user);
    }

    @PostMapping("/addLogins")
    public List<User> addLogins(@RequestBody List<User> users){
        return service.saveLogins(users);
    }

    @GetMapping("/logins")
    public List<User> findAllLogins() {
        return service.getLogins();
    }

    @GetMapping("/loginById/{id}")
    public User findLoginById(@PathVariable int id) {
        return service.getLoginById(id);
    }

    @GetMapping("/loginByUsername/{username}")
    public User findLoginByUsername(@PathVariable String username) {
        return service.getLoginByUsername(username);
    }

    @PutMapping("/updateLogin")
    public User updateUser(@RequestBody User user) {
        return service.updateLogin(user);
    }

    @DeleteMapping("/deleteLogin/{id}")
    public String deleteLogin(@PathVariable int id) {
        return service.deleteLoginById(id);
    }
}
