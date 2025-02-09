package com.example.authenticationservice.service.authentication;

import com.example.authenticationservice.dto.authentication.AuthenticationRequestDto;
import com.example.authenticationservice.dto.register.RegisterRequestDto;
import com.example.authenticationservice.entity.user.User;
import com.example.authenticationservice.service.user.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.authenticationservice.entity.user.Role.USER;

@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final UserDomainService userDomainService;
    private final AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String register(RegisterRequestDto request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(USER)
                .build();
        user = userDomainService.save(user);

        return jwtService.generateToken(user);
    }

    public String authenticate(AuthenticationRequestDto request) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        User user = userDomainService.findByUsername(request.getUsername());

        return jwtService.generateToken(user);
    }
}
