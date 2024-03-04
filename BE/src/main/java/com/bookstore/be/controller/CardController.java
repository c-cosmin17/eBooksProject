package com.bookstore.be.controller;

import com.bookstore.be.model.Card;
import com.bookstore.be.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardController {
    @Autowired
    private CardService service;

    @PostMapping("/addCard")
    public Card addCard(@RequestBody Card card){
        return service.saveCard(card);
    }

    @PostMapping("/addCards")
    public List<Card> addCards(@RequestBody List<Card> cards){
        return service.saveCards(cards);
    }

    @GetMapping("/cards")
    public List<Card>  findAllCards() {
        return service.getCards();
    }

    @GetMapping("/cardById/{id}")
    public Card findCardById(@PathVariable int id) {
        return service.getCardById(id);
    }

    @GetMapping("/cardByName/{name}")
    public Card findCardByName(@PathVariable String name) {
        return service.getCardByName(name);
    }

    @PutMapping("/updateCard")
    public Card updateCard(@RequestBody Card card) {
        return service.updateCard(card);
    }

    @DeleteMapping("/deleteCard/{id}")
    public String deleteCard(@PathVariable int id) {
        return service.deleteCardById(id);
    }
}
