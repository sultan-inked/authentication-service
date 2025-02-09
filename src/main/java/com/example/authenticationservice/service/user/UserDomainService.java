package com.example.authenticationservice.service.user;

import com.example.authenticationservice.entity.user.User;
import com.example.authenticationservice.exception.user.UserWithUsernameNotFoundException;
import com.example.authenticationservice.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserDomainService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new UserWithUsernameNotFoundException(username));
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
