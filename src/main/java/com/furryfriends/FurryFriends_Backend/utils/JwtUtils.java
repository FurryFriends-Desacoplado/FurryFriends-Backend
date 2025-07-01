package com.furryfriends.FurryFriends_Backend.utils;

import com.furryfriends.FurryFriends_Backend.security.CustomUserDetails;
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
public class JwtUtils {

    @Value("${jwt.secret.base64}")
    private String base64SecretKey;

    private SecretKey secretKey;

    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000; // 1 día en milisegundos

    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(base64SecretKey);
        if (keyBytes.length < 64) {
            throw new IllegalArgumentException("La clave debe tener al menos 512 bits (64 bytes) para HS512.");
        }
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Authentication authentication) {
        CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername()) // correo
                .claim("userId", userPrincipal.getUser().getId()) // id como claim
                .claim("role", userPrincipal.getUser().getRole()) // puedes agregar más si quieres
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            Number userId = claims.get("userId", Number.class);
            return userId != null ? userId.longValue() : null;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("No se pudo extraer el ID del token");
        }
    }
}
