package com.example.authenticationservice.exception.handler;

import com.example.authenticationservice.exception.global.ApiException;
import com.example.authenticationservice.exception.global.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException exception) {
        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(ErrorResponse.builder()
                        .httpStatus(exception.getHttpStatus())
                        .message(exception.getMessage())
                        .build());
    }
}
