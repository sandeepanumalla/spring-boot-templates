package com.example.securityinmemory.service;

import com.example.securityinmemory.config.CustomAuthenticationProvider;
import com.example.securityinmemory.repository.UserRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class AuthService {

    private final CustomAuthenticationProvider authenticationProvider;

    private final UserRepository userRepository;


    public AuthService(CustomAuthenticationProvider authenticationProvider, UserRepository userRepository) {
        this.authenticationProvider = authenticationProvider;
        this.userRepository = userRepository;
    }

    public void authenticate(User user) {
        Authentication credentials = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        authenticationProvider.authenticate(credentials);
    }

    public void register(User user) {
        try {
            User savedUser = userRepository.save(user);
        } catch (ConstraintViolationException exception) {
            exception.printStackTrace();
        }
    }
}
