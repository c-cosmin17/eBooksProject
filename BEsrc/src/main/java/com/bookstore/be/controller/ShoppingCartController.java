package com.bookstore.be.controller;

import com.bookstore.be.model.Book;
import com.bookstore.be.model.ShoppingCart;
import com.bookstore.be.model.ShoppingCartId;
import com.bookstore.be.service.BookService;
import com.bookstore.be.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService service;

    @PostMapping("/addShoppingCart")
    public ShoppingCart addShoppingCart(@RequestBody ShoppingCart shoppingCart){
        return service.saveShoppingCart(shoppingCart);
    }

    @PostMapping("/addShoppingCarts")
    public List<ShoppingCart> addShoppingCarts(@RequestBody List<ShoppingCart> shoppingCarts){
        return service.saveShoppingCarts(shoppingCarts);
    }

    @GetMapping("/shoppingCarts/{userId}")
    public List<ShoppingCart> findAllShoppingCarts(@PathVariable int userId) {
        return service.getShoppingCarts(userId);
    }

    @GetMapping("/shoppingCartById")
    public ShoppingCart findShoppingCartById(@RequestBody ShoppingCartId shoppingCartId) {
        return service.getShoppingCartById(shoppingCartId);
    }



//    @PutMapping("/updateShoppingCart")
//    public ShoppingCart updateShoppingCart(@RequestBody ShoppingCart shoppingCart) {
//        return service.updateShoppingCart(shoppingCart);
//    }

    @DeleteMapping("/deleteShoppingCart")
    public String deleteShoppingCart(@RequestBody ShoppingCartId shoppingCartId) {
        return service.deleteShoppingCartById(shoppingCartId);
    }
    @DeleteMapping("/deleteShoppingCart/{userId}")
    public String deleteShoppingCart(@PathVariable int userId) {
        return service.deleteShoppingCartByUserId(userId);
    }
}
