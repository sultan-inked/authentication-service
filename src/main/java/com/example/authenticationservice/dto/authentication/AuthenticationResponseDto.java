package com.example.authenticationservice.dto.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class AuthenticationResponseDto {
    private String token;
}
