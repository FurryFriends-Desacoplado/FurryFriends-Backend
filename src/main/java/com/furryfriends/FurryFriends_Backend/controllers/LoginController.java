package com.furryfriends.FurryFriends_Backend.controllers;

import com.furryfriends.FurryFriends_Backend.dto.LoginRequest;
import com.furryfriends.FurryFriends_Backend.security.CustomUserDetails;
import com.furryfriends.FurryFriends_Backend.security.JwtTokenProvider;
import com.furryfriends.FurryFriends_Backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager,
                           JwtTokenProvider jwtTokenProvider,
                           UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            System.out.println("Intentando login con email: " + loginRequest.getEmail());

            if (!userService.existsByEmail(loginRequest.getEmail())) {
                return ResponseEntity.status(404).body(Map.of("error", "Usuario no encontrado o no registrado"));
            }

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            String jwt = jwtTokenProvider.generateToken(authentication);

            String role = userDetails.getUser().getRole();
            if (role == null || role.isEmpty()) {
                // En caso de que role esté vacío, asignamos un default
                role = "ROLE_USER";
            }

            String nombre = userDetails.getUser().getNombre();

            String apellido = userDetails.getUser().getApellido();

            // Retornamos el rol explicitamente en la respuesta JSON
            return ResponseEntity.ok(Map.of(
                    "email", userDetails.getUsername(),
                    "token", jwt,
                    "role", role,
                    "nombre", nombre,
                    "apellido", apellido,
                    "id", userDetails.getUser().getId()
            ));

        } catch (Exception ex) {
            System.out.println("Error autenticando: " + ex.getMessage());
            return ResponseEntity.status(401).body(Map.of("error", "Credenciales inválidas"));
        }
    }
}
