package com.example.authenticationservice.exception.authentication.registration;

import com.example.authenticationservice.exception.global.ApiException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class UsernameAlreadyExistException extends ApiException {
    private static final String MESSAGE = "Username: %s already exist";

    public UsernameAlreadyExistException(String username) {
        super(BAD_REQUEST, MESSAGE, username);
    }
}
