package com.example.AuthService;

import com.example.AuthService.ResponseEntity.SuccessEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping ("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<SuccessEntity> register(@RequestBody UserDtos userbody){
        authService.registerUser(userbody);
        return ResponseEntity.ok(
                SuccessEntity
                        .builder()
                        .token(
                                authService.generateToken(userbody.email())
                        )
                        .user(UserEntity
                                .builder()
                                .email(userbody.email())
                                .firstName(userbody.firstName())
                                .lastName(userbody.lastName())
                                .role(userbody.role())
                                .build())
                        .build()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<SuccessEntity> login(@RequestBody AuthLogin userCredentials){
        return authService.login(userCredentials);
    }

//    @PostMapping("/")
}
