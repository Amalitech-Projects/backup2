package com.example.AuthService.Models;


import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Verifications {

    @Id
    private String _id;
    private String email;
    private String code;

}
