package com.example.UserService;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {

    public Optional<UserEntity> findById(String id);
    public List<UserCustomResponse> findByFirstName(String FirstName);
    public Optional<UserEntity> findByEmail(String Email);

    public void deleteById(String id);

    public UserEntity insert(UserDtos userBody);

    UserDtos insert(Optional<UserEntity> userExists);
}
