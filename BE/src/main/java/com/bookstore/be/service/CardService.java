package com.bookstore.be.service;

import com.bookstore.be.model.Card;
import com.bookstore.be.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
    @Autowired
    private CardRepository repository;

    public Card saveCard(Card card){
        return repository.save(card);
    }

    public List<Card> saveCards(List<Card> cards){
        return repository.saveAll(cards);
    }

    public List<Card> getCards(){
        return repository.findAll();
    }

    public Card getCardById(int id){
        return repository.findById(id).orElse(null);
    }

    public Card getCardByName(String name){
        return repository.findByName(name);
    }

    public String deleteCardById(int id){
        repository.deleteById(id);
        return "Card removed: " + id;
    }

    public Card updateCard(Card card){
        Card existingCard = repository.findById(card.getId()).orElse(null);
        if (existingCard != null){
            existingCard.setName(card.getName());
            return repository.save(existingCard);
        } else
            return repository.save(card);
    }
}
