package com.example.AuthService;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthRepository extends MongoRepository<UserEntity, String> {
    public Optional<UserEntity> findById(String id);
    public List<UserDtos> findByFirstName(String FirstName);
    public Optional<UserEntity> findByEmail(String Email);

    public void deleteById(String id);

    public UserEntity insert(UserDtos userBody);
}
