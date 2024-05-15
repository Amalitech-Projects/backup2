package com.example.AuthService;

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
    private String firstName;
    private String lastName;
    private String email;

    @JsonIgnore
    private String password;
    private String role;

}
