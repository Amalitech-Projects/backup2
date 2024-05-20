package com.example.AuthService;

import com.example.AuthService.Dtos.UserDtos;
import com.example.AuthService.ErrorHandlerExceptions.LoginErrorException;
import com.example.AuthService.Models.ForgotPasswords;
import com.example.AuthService.Models.Verifications;
import com.example.AuthService.ResponseEntity.SuccessEntity;
import lombok.RequiredArgsConstructor;
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
        System.out.println("Works");
        return ResponseEntity.ok(
                SuccessEntity
                        .builder()
                        .token(
                                authService.generateJwtToken(userbody.email())
                        )
                        .user(UserDtos
                                .builder()
                                .email(userbody.email())
                                .username(userbody.username())
                                .role(userbody.role())
                                .build())
                        .build()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthLogin userCredentials){
          boolean match = authService.login(userCredentials);
          if(match) return ResponseEntity.ok(authService.generateJwtToken(userCredentials.email()));
          else throw new LoginErrorException();
    }

    @PostMapping("/verify-email")
    public ResponseEntity<String> verifyEmail(@RequestBody Verifications verificationData){
        return  ResponseEntity.ok(authService.verifyRegisteredUser(verificationData));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswords forgotBody){
          authService.forgotPassword(forgotBody);
          return ResponseEntity.ok("Email Sent Successfully!");
    }

    @PutMapping("/verify-forgot-password")
    public ResponseEntity<String> verifyForgotPassword(@RequestBody ForgotPasswords forgotBody){
        return ResponseEntity.ok(authService.verifyForgotPassword(forgotBody));
    }

    @GetMapping("/google")
    public ResponseEntity<String> google(){
        return ResponseEntity.ok("Login Successful");
    }

    @PostMapping("/validate")
        public ResponseEntity<String> validateToken(@RequestBody String token){
            authService.validateJwtToken(token);
            return ResponseEntity.ok("Successfully Validated");
        }
}
