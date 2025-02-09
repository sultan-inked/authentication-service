package com.example.authenticationservice.service.user;

import com.example.authenticationservice.dto.register.ChangePasswordRequestDto;
import com.example.authenticationservice.entity.user.User;
import com.example.authenticationservice.exception.authentication.PasswordsNotMatchException;
import com.example.authenticationservice.exception.authentication.WrongUsernameOrPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@RequiredArgsConstructor
@Service
public class UserCredentialsService {
    private final PasswordEncoder passwordEncoder;
    private final UserDomainService userDomainService;

    public void changePassword(ChangePasswordRequestDto request, Principal connectedUser) {
        User user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        if (!request.getUsername().equals(user.getUsername())) {
            throw new WrongUsernameOrPasswordException();
        }
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new WrongUsernameOrPasswordException();
        }
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new PasswordsNotMatchException();
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        userDomainService.save(user);
    }
}
