package com.example.AuthService.ErrorHandlerExceptions;

public class InvalidUserException extends RuntimeException{
      public InvalidUserException(String message) {
          super(message);
      }
}
