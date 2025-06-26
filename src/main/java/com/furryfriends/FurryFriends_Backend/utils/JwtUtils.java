package com.furryfriends.FurryFriends_Backend.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${jwt.secret.base64}")
    private String base64SecretKey;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(base64SecretKey);
        if (keyBytes.length < 64) {
            throw new IllegalArgumentException("La clave debe tener al menos 512 bits (64 bytes) para HS512.");
        }
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateJwtToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 día
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }
}
