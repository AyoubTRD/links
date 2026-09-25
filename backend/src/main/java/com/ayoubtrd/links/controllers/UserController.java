package com.ayoubtrd.links.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ayoubtrd.links.auth.JwtService;
import com.ayoubtrd.links.controllers.data.LoginRequestBody;
import com.ayoubtrd.links.services.AuthService;


@RestController
@RequestMapping("/users")
public class UserController {
    private UserDetailsService userDetailsService;

    private AuthenticationManager authManager;

    private JwtService jwtService;

    private AuthService authService;

    public UserController(UserDetailsService userDetailsService, AuthenticationManager authManager,
            JwtService jwtService, AuthService authService) {
        this.userDetailsService = userDetailsService;
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestBody body) {
        System.out.println("In /login");
        Authentication auth = UsernamePasswordAuthenticationToken
                .unauthenticated(userDetailsService.loadUserByUsername(body.getUsername()), body.getPassword());
        
        auth = authManager.authenticate(auth);
        if (!auth.isAuthenticated()) throw new BadCredentialsException("Invalid username and password");

        String token = jwtService.generateToken(body.getUsername());
        return token;
    }

    @PostMapping("/register")
    public String register(@RequestBody LoginRequestBody body) {
        System.out.println("in register");
        String token = authService.register(body.getUsername(), body.getPassword());

        return token;
    }

    @GetMapping("/me")
    public String me() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
