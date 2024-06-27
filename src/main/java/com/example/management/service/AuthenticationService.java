package com.example.management.service;

import com.example.management.model.User;
import com.example.management.repository.UserRepository;
import com.example.management.security.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService{

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenUtils tokenUtils;

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public String login(String username, String password) {
        final Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        User user = (User) authentication.getPrincipal();
        return tokenUtils.generateToken(user.getUsername());
    }
}
