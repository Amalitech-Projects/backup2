package com.Travely.EmailService.Dtos;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ForgotPassword {

    private String email;
    private String token;
    private String name;

}
