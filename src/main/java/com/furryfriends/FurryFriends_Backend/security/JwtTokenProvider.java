package com.furryfriends.FurryFriends_Backend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret.base64}")
    private String base64SecretKey;

    private SecretKey secretKey;

    private final long EXPIRATION_TIME = 86400000; // 1 día en ms

    @PostConstruct
    public void init() {
        System.out.println("jwt.secret.base64 = '" + base64SecretKey + "'");
        byte[] keyBytes = Base64.getDecoder().decode(base64SecretKey);
        System.out.println("Clave JWT base64 cargada, longitud en bits: " + (keyBytes.length * 8));
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }
    
    public String generateToken(Authentication authentication) {
        CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .claim("role", userPrincipal.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (JwtException ex) {
            System.out.println("Error validando token JWT: " + ex.getMessage());
        }
        return false;
    }
}

