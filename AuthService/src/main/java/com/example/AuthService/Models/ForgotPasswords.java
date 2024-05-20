package com.example.AuthService.Models;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class ForgotPasswords {

    @Id
    private String id;
    private String code;
    private String email;
    private String oldPassword;
    private String newPassword;

}
