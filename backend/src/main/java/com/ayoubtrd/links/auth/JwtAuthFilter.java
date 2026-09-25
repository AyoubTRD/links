package com.ayoubtrd.links.auth;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends HttpFilter {
    private AuthenticationManager authenticationManager;

    public JwtAuthFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || authHeader.isEmpty())
            chain.doFilter(request, response);
        else {
            String token = authHeader.replace("Bearer ", "");
            SecurityContext context = SecurityContextHolder.getContext();

            BearerTokenAuthenticationToken auth = new BearerTokenAuthenticationToken(token);
            auth.setAuthenticated(false);

            Authentication newAuth = authenticationManager.authenticate(auth);
            context.setAuthentication(newAuth);

            chain.doFilter(request, response);
        }
    }

}
