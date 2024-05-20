package com.example.AuthService.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class User {

    private String id;
    private String username;
    private String email;
    private String password;
    private String role;
    private String userType;
    private boolean isVerified;

}
