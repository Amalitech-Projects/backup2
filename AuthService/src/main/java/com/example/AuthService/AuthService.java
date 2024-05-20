package com.example.AuthService;

import com.example.AuthService.Config.AuthConfig;
import com.example.AuthService.Config.JwtService;
import com.example.AuthService.ErrorHandlerExceptions.InvalidUserException;
import com.example.AuthService.ErrorHandlerExceptions.LoginErrorException;
import com.example.AuthService.Models.ForgotPasswords;
import com.example.AuthService.Models.User;
import com.example.AuthService.Dtos.UserDtos;
import com.example.AuthService.Models.Verifications;
import com.example.AuthService.Repositories.AuthRepository;
import com.example.AuthService.Repositories.ForgotPasswordRepository;
import com.example.AuthService.Repositories.VerificationCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    private final VerificationCodeRepository verificationCodeRepository;
    private final ForgotPasswordRepository forgotPasswordRepository;

    private final JwtService jwtService;

    private final RestTemplate restTemplate;

    private final AuthConfig authConfig;

    public void registerUser(UserDtos user){
        boolean isEmailAvailable = authRepository.findByEmail(user.email()).isPresent();
        User newUser = User
                .builder()
                .username(user.username())
                .email(user.email())
                .password(authConfig.passwordEncoder().encode(user.password()))
                .role("USER")
                .build();


        validatePassword(user.password());

        if(isEmailAvailable)
            throw new InvalidUserException("Email already in use!");

        authRepository.insert(newUser);

        String code = generateEmailVerificationToken();

        verificationCodeRepository.insert(
                Verifications
                        .builder()
                        .email(user.email())
                        .code(code)
                        .build()
        );

        // Construct the URL using the service name registered in Eureka
        String emailServiceUrl = "http://localhost:4009/api/v1/email-service/new-user";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create request payload (assuming JSON format)
        String requestBody = "{\"email\": \"" + user.email() + "\", \"token\": \"" + code + "\", \"name\": \"" + user.username() + "\"}";

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Send POST request to the email service
        restTemplate.postForObject(emailServiceUrl, requestEntity, String.class);
    }

    public void registerAdmin(UserDtos user){
        boolean isEmailAvailable = authRepository.findByEmail(user.email()).isPresent();
        User newUser = User
                .builder()
                .username(user.username())
                .email(user.email())
                .password(authConfig.passwordEncoder().encode(user.password()))
                .role("ADMIN")
                .build();

        if(isEmailAvailable)
            throw new InvalidUserException("Email already in use!");

        authRepository.insert(newUser);
    }

    public boolean login(AuthLogin userCredentials){
        User user = authRepository.findByEmail(userCredentials.email()).orElseThrow(LoginErrorException::new);
         return authConfig.comparePassword(userCredentials.password(), user.getPassword()); // false - true
    }

    public Optional<User> findByEmail(String email){
        return authRepository.findByEmail(email);
    }

    public String generateJwtToken(String email) {
        return jwtService.generateToken(email);
    }

    public void validateJwtToken(String token) {
        jwtService.validateToken(token);
    }

    public String generateEmailVerificationToken(){
            return UUID.randomUUID().toString();
    }

    public String verifyRegisteredUser(Verifications requestVerification) {
        Verifications isValid = verificationCodeRepository.findByCode(requestVerification.getCode());
        System.out.println(isValid);
        Optional<User> validateUser = authRepository.findByEmail(requestVerification.getEmail());

        if (isValid != null && validateUser.isPresent()) {
            User userToUpdate = validateUser.get();
            userToUpdate.setVerified(true);
            authRepository.save(userToUpdate);
            verificationCodeRepository.delete(isValid); // Remove the used verification code
            return "User Successfully Verified";
        } else {
            return "Verification code not valid or user not found";
        }
    }

    public void forgotPassword(ForgotPasswords requestData){

        String code = generateEmailVerificationToken();

        Optional<User> validUser = authRepository.findByEmail(requestData.getEmail());

        forgotPasswordRepository.insert(ForgotPasswords
                .builder()
                        .code(code)
                        .email(requestData.getEmail())
                .build());

        // Construct the URL using the service name registered in Eureka
        String emailServiceUrl = "http://localhost:4009/api/v1/email-service/forgot-password";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create request payload (assuming JSON format)
        String requestBody = "{\"email\": \"" + requestData.getEmail() + "\", \"token\": \"" + code + "\", \"name\": \"" + validUser.get().getUsername() + "\"}";

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // Send POST request to the email service
        restTemplate.postForObject(emailServiceUrl, requestEntity, String.class);

    }

    public String verifyForgotPassword(ForgotPasswords forgotPasswordBody) {
        // Validate the verification code
        ForgotPasswords isValid = forgotPasswordRepository.findByCode(forgotPasswordBody.getCode());
        Optional<User> validateUser = authRepository.findByEmail(forgotPasswordBody.getEmail());

        if (isValid != null && validateUser.isPresent()) {
            User userToUpdate = validateUser.get();

            // Check if the new password has been used before
            List<ForgotPasswords> passwordHistory = forgotPasswordRepository.findByEmail(forgotPasswordBody.getEmail());
            for (ForgotPasswords record : passwordHistory) {
                if (authConfig.passwordEncoder().matches(forgotPasswordBody.getNewPassword(), record.getOldPassword())) {
                    return "The provided new password has already been used before.";
                }
            }

            // Update the password
            userToUpdate.setPassword(authConfig.passwordEncoder().encode(forgotPasswordBody.getNewPassword()));
            isValid.setNewPassword("Complete");
            authRepository.save(userToUpdate);
            forgotPasswordRepository.delete(isValid); // Remove the used verification code

            return "User Successfully Verified";
        } else {
            return "Verification code not valid or user not found";
        }
    }


    private void validatePassword(String password) {
        // Regular expression to check password requirements
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        if (!password.matches(passwordPattern)) {
            throw new InvalidUserException("Password must be at least 8 characters long and include a combination of upper, lower case letters, special characters, and numbers.");
        }
    }
}
