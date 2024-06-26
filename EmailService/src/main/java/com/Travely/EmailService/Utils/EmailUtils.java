package com.Travely.EmailService.Utils;


import org.springframework.stereotype.Service;

@Service
public class EmailUtils {
    public static String getEmailMessage(String name, String host, String token) {
        return "Hello " + name + ",\n\nYour new account has been created. Please click the link below to verify your account. \n\n" +
                getVerificationUrl(host, token, name) + "\n\nThe support Team";
    }

    public static String getVerificationUrl(String host, String token, String email) {
        return  host + "?code=" + token + "&?email=" + email;
    }
}
