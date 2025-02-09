package com.example.authenticationservice.controller.auth;

import com.example.authenticationservice.dto.authentication.AuthenticationRequestDto;
import com.example.authenticationservice.dto.authentication.AuthenticationResponseDto;
import com.example.authenticationservice.dto.register.RegisterRequestDto;
import com.example.authenticationservice.service.authentication.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public AuthenticationResponseDto register(@RequestBody RegisterRequestDto request) {
        String jwtToken = authenticationService.register(request);

        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }

    @PostMapping("/authentication")
    public AuthenticationResponseDto authentication(@RequestBody AuthenticationRequestDto request) {
        String jwtToken = authenticationService.authenticate(request);

        return AuthenticationResponseDto.builder()
                .token(jwtToken)
                .build();
    }
}
