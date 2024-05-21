package com.example.AuthService.Response;


import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class LoginResponse {

    private String token;
    private String message;
}
