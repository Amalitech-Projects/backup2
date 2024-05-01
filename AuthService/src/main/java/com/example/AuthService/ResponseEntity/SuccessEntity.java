package com.example.AuthService.ResponseEntity;

import com.example.AuthService.UserEntity;
import lombok.*;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class SuccessEntity {

    private String token;
    private UserEntity user;


}
