package com.example.AuthService.ErrorHandlerExceptions.exceptionDto;

import lombok.Builder;

@Builder
public record ErrorResponse(int status, String message) {
}
