package com.example.UserService.Dtos;


import jakarta.annotation.Generated;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
@Document
public class RequestPasswordChange {

    @Id
    String Id;

    private UUID token;
}
