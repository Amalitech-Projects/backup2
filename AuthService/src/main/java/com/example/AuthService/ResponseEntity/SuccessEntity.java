package com.example.AuthService.ResponseEntity;

import com.example.AuthService.User;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class SuccessEntity {

    private String token;
    private User user;


}
