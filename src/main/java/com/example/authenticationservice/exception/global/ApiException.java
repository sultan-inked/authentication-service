package com.example.authenticationservice.exception.global;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException {
    private final HttpStatus httpStatus;

    public ApiException(HttpStatus httpStatus, String message, Object... args) {
        super(String.format(message, args));
        this.httpStatus = httpStatus;
    }
}
