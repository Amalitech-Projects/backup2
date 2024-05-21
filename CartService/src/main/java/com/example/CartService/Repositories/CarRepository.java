package com.example.CartService.Repositories;

import com.example.CartService.Cars;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends MongoRepository<Cars, String> {
    Optional<Object> findByName(String name);
}
