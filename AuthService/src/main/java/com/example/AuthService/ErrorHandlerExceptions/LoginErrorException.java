package com.example.AuthService.ErrorHandlerExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.UNAUTHORIZED, reason="Not authorized")  // 404
public class LoginErrorException extends RuntimeException{
}