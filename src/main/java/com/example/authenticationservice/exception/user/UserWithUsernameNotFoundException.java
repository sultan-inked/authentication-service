package com.example.authenticationservice.exception.user;

import com.example.authenticationservice.exception.global.ApiException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class UserWithUsernameNotFoundException extends ApiException {
    private static final String MESSAGE = "User with username: %s not found";

    public UserWithUsernameNotFoundException(String username) {
        super(BAD_REQUEST, MESSAGE, username);
    }
}
