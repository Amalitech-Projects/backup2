package com.example.AuthService;

import com.example.AuthService.Config.AuthConfig;
import com.example.AuthService.Config.JwtService;
import com.example.AuthService.ErrorHandlerExceptions.InvalidUserException;
import com.example.AuthService.ErrorHandlerExceptions.LoginErrorException;
import com.example.AuthService.ResponseEntity.SuccessEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    private final JwtService jwtService;

    private final AuthConfig authConfig;

    public void registerUser(UserDtos user){
        boolean isEmailAvailable = authRepository.findByEmail(user.email()).isPresent();
        UserEntity newUser = UserEntity
                .builder()
                .firstName(user.firstName())
                .lastName(user.lastName())
                .email(user.email())
                .password(authConfig.passwordEncoder().encode(user.password()))
                .role("USER")
                .build();

        if(isEmailAvailable)
            throw new InvalidUserException("Email already in use!");

        authRepository.insert(newUser);


    }

    public boolean login(AuthLogin userCredentials){
        UserEntity user = authRepository.findByEmail(userCredentials.email()).orElseThrow(LoginErrorException::new);
         return authConfig.comparePassword(userCredentials.password(), user.getPassword()); // false - true
    }

    public String generateToken(String email) {
        return jwtService.generateToken(email);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

}
