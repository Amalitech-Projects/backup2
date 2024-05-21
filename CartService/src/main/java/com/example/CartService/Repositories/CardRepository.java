package com.example.CartService.Repositories;

import com.example.CartService.Card;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CardRepository extends MongoRepository<Card, String>{
    Optional<Card> findByUserId(String userId);
}
