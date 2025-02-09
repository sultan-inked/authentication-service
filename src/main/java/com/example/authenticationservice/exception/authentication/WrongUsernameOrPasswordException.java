package com.example.authenticationservice.exception.authentication;

import com.example.authenticationservice.exception.global.ApiException;

import static org.springframework.http.HttpStatus.FORBIDDEN;

public class WrongUsernameOrPasswordException extends ApiException {
    private static final String MESSAGE = "Wrong username or password";

    public WrongUsernameOrPasswordException() {
        super(FORBIDDEN, MESSAGE);
    }
}
