package com.example.AuthService;

import com.example.AuthService.ErrorHandlerExceptions.LoginErrorException;
import com.example.AuthService.ResponseEntity.SuccessEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
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
          boolean match = authService.login(userCredentials);
          if(match) return ResponseEntity.ok(SuccessEntity
                  .builder()
                          .token(authService.generateToken(userCredentials.email()))
                          .user(UserEntity
                                  .builder()
                                  .build())
                  .build());
          else throw new LoginErrorException();
    }

    @PostMapping("/validate")
        public ResponseEntity<String> validateToken(@RequestBody String token){
            authService.validateToken(token);
            return ResponseEntity.ok("Successfully Validated");
        }
}
