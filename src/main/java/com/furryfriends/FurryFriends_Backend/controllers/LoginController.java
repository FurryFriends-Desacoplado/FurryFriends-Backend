package com.furryfriends.FurryFriends_Backend.controllers;

import com.furryfriends.FurryFriends_Backend.dto.LoginRequest;
import com.furryfriends.FurryFriends_Backend.entities.User;
import com.furryfriends.FurryFriends_Backend.enums.EnrollmentStatus;
import com.furryfriends.FurryFriends_Backend.security.CustomUserDetails;
import com.furryfriends.FurryFriends_Backend.services.UserService;
import com.furryfriends.FurryFriends_Backend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager,
                           JwtUtils jwtUtils,
                           UserService userService,
                           PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            System.out.println("Intentando login con email: " + loginRequest.getEmail());

            // Paso 1: Verifica si el usuario existe
            Optional<User> userOpt = userService.findByEmail(loginRequest.getEmail());
            if (userOpt.isEmpty()) {
                return ResponseEntity.status(404).body(Map.of("error", "Usuario no encontrado o no registrado"));
            }

            User user = userOpt.get();

            // Paso 2: Verifica si la cuenta está activa
            if (user.getEnrollmentStatus() != EnrollmentStatus.Activa) {
                return ResponseEntity.status(403).body(Map.of("error", "Tu cuenta está inactiva. Comunícate con soporte."));
            }

            // Paso 3: Verifica si la contraseña es correcta
            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return ResponseEntity.status(401).body(Map.of("error", "La contraseña es incorrecta para el correo ingresado"));
            }

            // Paso 4: Autentica y genera el token con userId
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            String jwt = jwtUtils.generateToken(authentication); // ✅ Genera token con userId como claim

            return ResponseEntity.ok(Map.of(
                    "email", userDetails.getUsername(),
                    "token", jwt,
                    "role", userDetails.getUser().getRole(),
                    "nombre", userDetails.getUser().getNombre(),
                    "apellido", userDetails.getUser().getApellido(),
                    "id", userDetails.getUser().getId()
            ));

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "Error interno del servidor"));
        }
    }
}
