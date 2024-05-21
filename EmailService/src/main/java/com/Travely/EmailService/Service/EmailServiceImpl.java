package com.Travely.EmailService.Service;

import com.Travely.EmailService.Dtos.ForgotPassword;
import com.Travely.EmailService.Dtos.NewUser;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

import static com.Travely.EmailService.Utils.EmailUtils.getVerificationUrl;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    private static final String UTF_8_ENCODING = "UTF-8";
    private static final String NEW_USER_TEMPLATE = "verifyUser";
    private static final String FORGOT_PASSWORD_TEMPLATE = "forgotPassword";
    private static final String TEXT_HTML_ENCODING = "text/html";


    @Value("${spring.mail.verify.host}")
    private String host;

    @Override
    public void registedEmail(NewUser data) {
        try {
            Context context = new Context();
            context.setVariables(Map.of("name", data.getName(), "url", getVerificationUrl("http://localhost:4200/email-verification", data.getToken(), data.getEmail())));
            String text = templateEngine.process(NEW_USER_TEMPLATE, context);

            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject("Verify your Email " + data.getName());
            String fromEmail = "panditmckenzie@gmail.com";
            helper.setFrom(fromEmail);
            helper.setTo(data.getEmail());
            helper.setText(text, true);

            emailSender.send(message);
        } catch (Exception exception) {
            System.out.println("Exception occurred: " + exception.getMessage());
            exception.printStackTrace();
            throw new RuntimeException("Failed to send email: " + exception.getMessage());
        }
    }

    @Override
    public void forgotPasswordEmail(ForgotPassword data) {
        try {
            Context context = new Context();
            context.setVariables(Map.of("name", data.getName(), "url", getVerificationUrl("http" + "://" + "localhost:4200/forgot-password", data.getToken(), data.getEmail())));
            String text = templateEngine.process(FORGOT_PASSWORD_TEMPLATE, context);

            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, UTF_8_ENCODING);
            helper.setPriority(1);
            helper.setSubject("Reset Password");
            String fromEmail = "panditmckenzie@gmail.com";
            helper.setFrom(fromEmail);
            helper.setTo(data.getEmail());
            helper.setText(text, true);

            emailSender.send(message);
        } catch (Exception exception) {
            System.out.println("Exception occurred: " + exception.getMessage());
            exception.printStackTrace();
            throw new RuntimeException("Failed to send email: " + exception.getMessage());
        }
    }

    private MimeMessage getMimeMessage() {
        return emailSender.createMimeMessage();
    }

    private String getContentId(String filename) {
        return "<" + filename + ">";
    }
}