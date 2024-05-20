package com.example.AuthService.Dtos;

import lombok.Builder;

@Builder
public record UserDtos( String username, String email, String password, String role) {
}
