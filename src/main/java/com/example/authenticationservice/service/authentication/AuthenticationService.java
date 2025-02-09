package com.example.authenticationservice.service.authentication;

import com.example.authenticationservice.dto.authentication.AuthenticationRequestDto;
import com.example.authenticationservice.dto.register.RegisterRequestDto;
import com.example.authenticationservice.entity.user.User;
import com.example.authenticationservice.exception.authentication.PasswordsNotMatchException;
import com.example.authenticationservice.exception.authentication.WrongUsernameOrPasswordException;
import com.example.authenticationservice.exception.authentication.registration.UsernameAlreadyExistException;
import com.example.authenticationservice.exception.user.UserWithUsernameNotFoundException;
import com.example.authenticationservice.service.user.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
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
        if (!request.getPassword().equals(request.getConfirmationPassword())) {
            throw new PasswordsNotMatchException();
        }
        if (userDomainService.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistException(request.getUsername());
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(USER)
                .build();
        user = userDomainService.save(user);

        return jwtService.generateToken(user);
    }

    public String authenticate(AuthenticationRequestDto request) {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            User user = userDomainService.findByUsername(request.getUsername());

            return jwtService.generateToken(user);
        } catch (UserWithUsernameNotFoundException | InternalAuthenticationServiceException | BadCredentialsException e) {
            throw new WrongUsernameOrPasswordException();
        }
    }
}
