package com.example.CartService;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {
    List<Cart> findByUserId(String userId);

    Optional<Cart> findById(String id);
}
