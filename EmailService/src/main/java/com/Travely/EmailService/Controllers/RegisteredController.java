package com.Travely.EmailService.Controllers;

import com.Travely.EmailService.Service.RegisteredService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/email-service")
public class RegisteredController {

    @Autowired
    RegisteredService registeredService;

    @PostMapping("/new-user")
    public ResponseEntity<String> sendNewUserEmail(String data){
        registeredService.sendNewUserEmail(data);
        return ResponseEntity.ok("Email sent Successfully");
    }
}
