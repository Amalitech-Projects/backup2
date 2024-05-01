package com.example.UserService;

import lombok.Builder;

@Builder
public record UserDtos( String firstName, String lastName, String email, String password) {
}
