package com.example.AuthService.Repositories;


import com.example.AuthService.Models.Verifications;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationCodeRepository extends MongoRepository<Verifications, String> {
    Verifications findByCode(String code);
}
