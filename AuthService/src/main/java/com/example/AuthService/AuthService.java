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
        if(!isEmailAvailable) {
            authRepository.insert(newUser);
        }
        else throw new InvalidUserException("Email already in use!");
    }

    public ResponseEntity<SuccessEntity> login(AuthLogin userCredentials){
        Optional<UserEntity> user = authRepository.findByEmail(userCredentials.email());

        if(user.isPresent()) {
            authConfig.comparePassword(userCredentials.password(), user.get().getPassword());
            return ResponseEntity.ok(SuccessEntity
                    .builder()
                    .token(generateToken(userCredentials.email()))
                    .user(UserEntity
                            .builder()
                            .id(user.get().getId())
                            .firstName(user.get().getFirstName())
                            .lastName(user.get().getLastName())
                            .email(user.get().getEmail())
                            .role(user.get().getRole())
                            .build())
                    .build());
        }
        else { throw new LoginErrorException();}
    }

    public String generateToken(String email) {
        return jwtService.generateToken(email);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

}
