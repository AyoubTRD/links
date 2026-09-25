package com.ayoubtrd.links.services;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ayoubtrd.links.auth.JwtService;
import com.ayoubtrd.links.entities.User;
import com.ayoubtrd.links.repositories.UserRepository;

@Service
public class AuthService {
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String register(String username, String password) {
        if (!username.equals("ayoub")) 
            throw new BadCredentialsException("Unsupported username");

        boolean userExists = userRepository.existsByUsername("ayoub");

        if (userExists) {
            throw new BadCredentialsException("Username taken");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword("{bcrypt}".concat(passwordEncoder.encode(password)));

        userRepository.save(user);

        String token = jwtService.generateToken(username);
        return token;
    }
}
