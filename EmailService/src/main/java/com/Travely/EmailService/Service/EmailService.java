package com.Travely.EmailService.Service;

import com.Travely.EmailService.Dtos.ForgotPassword;
import com.Travely.EmailService.Dtos.NewUser;

public interface EmailService {
    void registedEmail(NewUser data);
    void forgotPasswordEmail(ForgotPassword data);
}
