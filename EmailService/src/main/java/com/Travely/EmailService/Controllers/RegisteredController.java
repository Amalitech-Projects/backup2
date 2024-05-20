package com.Travely.EmailService.Controllers;

import com.Travely.EmailService.Dtos.ForgotPassword;
import com.Travely.EmailService.Dtos.NewUser;
import com.Travely.EmailService.Service.RegisteredService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/v1/email-service")
public class RegisteredController {

    private final RegisteredService registeredService;

    @PostMapping("/new-user")
    public ResponseEntity<String> sendNewUserEmail(@RequestBody NewUser data) {
        if (data.getEmail() == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        registeredService.sendNewUserEmail(data);
        return ResponseEntity.ok("New User Email sent Successfully");
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> sendForgotPasswordEmail(@RequestBody ForgotPassword data) {
        if (data == null) {
            throw new IllegalArgumentException("No credentials parsed!");
        }
        registeredService.forgotPassword(data);
        return ResponseEntity.ok("Password Email Sent Successfully");
    }


}
