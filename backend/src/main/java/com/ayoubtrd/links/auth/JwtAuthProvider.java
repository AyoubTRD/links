package com.ayoubtrd.links.auth;

import org.jspecify.annotations.Nullable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;

public class JwtAuthProvider implements AuthenticationProvider {
    private JwtService jwtService;
    private UserDetailsService userDetailsService;

    public JwtAuthProvider(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
        BearerTokenAuthenticationToken auth = (BearerTokenAuthenticationToken) authentication;

        String username = jwtService.verifyJwt(auth.getToken());

        Authentication newAuth = auth.toBuilder()
            .authenticated(true)
            .principal(userDetailsService.loadUserByUsername(username))
            .build();

        return newAuth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return BearerTokenAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
