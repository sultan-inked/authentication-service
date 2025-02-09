package com.example.authenticationservice.exception.authentication;

import com.example.authenticationservice.exception.global.ApiException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class PasswordsNotMatchException extends ApiException {
    private static final String MESSAGE = "Passwords not matching";

    public PasswordsNotMatchException() {
        super(BAD_REQUEST, MESSAGE);
    }
}
