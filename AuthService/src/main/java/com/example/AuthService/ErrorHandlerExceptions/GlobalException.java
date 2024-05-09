package com.example.AuthService.ErrorHandlerExceptions;

import com.example.AuthService.ErrorHandlerExceptions.exceptionDto.ErrorResponse;
import com.mongodb.DuplicateKeyException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler({ InvalidUserException.class })
    public ResponseEntity<ErrorResponse> handleInvalidUserException(Exception exception) {
        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .status(HttpStatus.FORBIDDEN.value())
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(errorResponse);
    }

    @ExceptionHandler({ DataIntegrityViolationException.class, DuplicateKeyException.class })
    public ResponseEntity<ErrorResponse> handleDuplicateKey(Exception exception) {
        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(errorResponse);
    }
}
