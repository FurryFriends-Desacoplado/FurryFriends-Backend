package com.furryfriends.FurryFriends_Backend.controllers;

import com.furryfriends.FurryFriends_Backend.entities.User;
import com.furryfriends.FurryFriends_Backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("{\"message\": \"El correo ya está registrado.\"}");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());

        // ⚠️ Este campo NO lo seteamos, para que use el valor por defecto de la BD
        user.setEnrollmentStatus(null);

        System.out.println(">>> enrollmentStatus antes de guardar: " + user.getEnrollmentStatus());
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }
}