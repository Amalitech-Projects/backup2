package com.example.AuthService.Repositories;

import com.example.AuthService.Models.ForgotPasswords;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ForgotPasswordRepository extends MongoRepository<ForgotPasswords, String> {
    ForgotPasswords findByCode(String code);

    List<ForgotPasswords> findByEmail(String email);
}
