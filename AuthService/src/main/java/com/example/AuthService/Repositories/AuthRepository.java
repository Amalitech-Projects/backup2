package com.example.AuthService.Repositories;

import com.example.AuthService.Dtos.UserDtos;
import com.example.AuthService.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthRepository extends MongoRepository<User, String> {
    public Optional<User> findById(String id);
    public Optional<User> findByEmail(String Email);

    public void deleteById(String id);

    public User insert(UserDtos userBody);
}
