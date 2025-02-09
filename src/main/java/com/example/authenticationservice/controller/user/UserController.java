package com.example.authenticationservice.controller.user;

import com.example.authenticationservice.dto.register.ChangePasswordRequestDto;
import com.example.authenticationservice.service.user.UserCredentialsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UserController {
    private final UserCredentialsService userCredentialsService;

    @GetMapping("/say-hello")
    public String sayHello() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();

        return String.format("Hello %s", username);
    }

    @PatchMapping("/change-password")
    public void changePassword(@RequestBody ChangePasswordRequestDto request, Principal connectedUser) {
        userCredentialsService.changePassword(request, connectedUser);
    }
}
