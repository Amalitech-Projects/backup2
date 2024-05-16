package com.example.UserService.Dtos;

import lombok.Builder;

@Builder
public record UserDtos( String firstName, String lastName, String email, String password) {
}
