package com.ayoubtrd.links.auth;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtService {
    @Value("${jwt.key}")
    private String keyStr;

    private SecretKey getKey() {
        byte[] bytes = Base64.getDecoder().decode(keyStr);

        return new SecretKeySpec(bytes, "HmacSHA256");
    }

    public String generateToken(String username) {
        Date expiration = new Date();
        expiration.setTime(expiration.getTime() + 24 * 60 * 60 * 1000);

        return Jwts.builder()
            .signWith(getKey())
            .expiration(expiration)
            .subject(username)
            .compact();
    }

    public String verifyJwt(String jwt) {
        Claims claims = Jwts.parser()
            .verifyWith(getKey())
            .build()
            .parseSignedClaims(jwt)
            .getPayload();

        String username = claims.getSubject();

        if (username == null || username.isEmpty())
            throw new BadCredentialsException("Invalid JWT token");
        
        if (claims.getExpiration().before(new Date()))
            throw new BadCredentialsException("Expired JWT token");

        return username;
    }
}
