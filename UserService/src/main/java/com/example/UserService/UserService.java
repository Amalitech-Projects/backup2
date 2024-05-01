package com.example.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserCustomResponse> getAllUsers() {
        return  userRepository.findAll().stream().map(user -> UserCustomResponse
                .builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build()).toList();
    }

    public Optional<UserEntity> getUserById(String id){
        return userRepository.findById(id);
    }

    public List<UserCustomResponse> getUserByFirstName(String firstName){
        return userRepository.findByFirstName(firstName);
    }

    public void deleteUser(String id){
        userRepository.deleteById(id);
    }

}