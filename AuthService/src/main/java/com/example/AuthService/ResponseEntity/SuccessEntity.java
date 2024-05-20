package com.example.AuthService.ResponseEntity;

import com.example.AuthService.Dtos.UserDtos;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class SuccessEntity {

    private String token;
    private UserDtos user;


}
