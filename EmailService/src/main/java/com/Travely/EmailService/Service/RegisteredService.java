package com.Travely.EmailService.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisteredService {

    public EmailServiceImpl emailService;

    public void sendNewUserEmail(String data){
        emailService.sendHtmlNewProjectEmail(data);
    }

}
