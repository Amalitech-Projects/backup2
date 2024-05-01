package com.example.AuthService;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class UserEntity {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;

}
