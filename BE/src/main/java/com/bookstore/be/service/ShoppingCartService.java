package com.bookstore.be.service;

import com.bookstore.be.model.Book;
import com.bookstore.be.model.Category;
import com.bookstore.be.model.ShoppingCart;
import com.bookstore.be.model.ShoppingCartId;
import com.bookstore.be.repository.BookRepository;
import com.bookstore.be.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartRepository repository;

    public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart){
        return repository.save(shoppingCart);
    }

    public List<ShoppingCart> saveShoppingCarts(List<ShoppingCart> shoppingCarts){
        return repository.saveAll(shoppingCarts);
    }

    public List<ShoppingCart> getShoppingCarts(int userId){
        List<ShoppingCart> shoppingCarts = repository.findAll();
        return findShoppingCartsById(shoppingCarts, userId);
    }

    public ShoppingCart getShoppingCartById(ShoppingCartId id){
        return repository.findById(id).orElse(null);
    }

    public String deleteShoppingCartByUserId(int userId){
        repository.deleteByCustomerId(userId);
        return "ShoppingCart removed: " + userId;
    }
    public String deleteShoppingCartById(ShoppingCartId id){
        repository.deleteById(id);
        return "ShoppingCart removed: " + id;
    }

//    public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart){
//        ShoppingCart existingShoppingCart = repository.findById(shoppingCart.getCustomerId()).orElse(null);
//        if (existingShoppingCart != null){
////            existingShoppingCart.setQuantity(shoppingCart.getQuantity());
//            return repository.save(existingShoppingCart);
//        } else
//            return repository.save(shoppingCart);
//    }

    private List<ShoppingCart> findShoppingCartsById(List<ShoppingCart> shoppingCarts, int userId) {
        return shoppingCarts.stream()
                .filter(shoppingCart -> shoppingCart.getCustomerId() == userId)
                .toList();
    }
}
