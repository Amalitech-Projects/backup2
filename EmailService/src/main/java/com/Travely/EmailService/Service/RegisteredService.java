package com.Travely.EmailService.Service;

import com.Travely.EmailService.Dtos.ForgotPassword;
import com.Travely.EmailService.Dtos.NewUser;
import com.Travely.EmailService.Utils.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.Travely.EmailService.Utils.EmailUtils.getVerificationUrl;


@RequiredArgsConstructor
@Service
public class RegisteredService {

    private final EmailServiceImpl emailService;
    private final EmailUtils emailUtils;

    public void sendNewUserEmail(NewUser userData){
        emailService.registedEmail(userData);
    }

    public void forgotPassword(ForgotPassword userData){
        emailService.forgotPasswordEmail(userData);
    }

}
