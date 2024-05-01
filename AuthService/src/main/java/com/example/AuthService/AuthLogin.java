package com.example.AuthService;

import lombok.*;

@Builder
public record AuthLogin (String email, String password){
}
