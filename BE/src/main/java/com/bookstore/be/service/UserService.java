package com.bookstore.be.service;

import com.bookstore.be.model.User;
import com.bookstore.be.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository repository;

    public User saveLogin(User user){
        return repository.save(user);
    }

    public List<User> saveLogins(List<User> users){
        return repository.saveAll(users);
    }

    public List<User> getLogins(){
        return repository.findAll();
    }

    public User getLoginById(int id){
        return repository.findById(id).orElse(null);
    }

    public User getLoginByUsername(String username){
        return repository.findByUsername(username).orElse(null);
    }

    public String deleteLoginById(int id){
        repository.deleteById(id);
        return "Login removed: " + id;
    }

    public User updateLogin(User user){
        User existingUser = repository.findById(user.getId()).orElse(null);
        if (existingUser != null){
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            return repository.save(existingUser);
        } else
            return repository.save(user);
    }
}
