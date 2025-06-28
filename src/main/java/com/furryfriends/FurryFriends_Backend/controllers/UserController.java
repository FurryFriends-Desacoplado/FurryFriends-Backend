package com.furryfriends.FurryFriends_Backend.controllers;

import com.furryfriends.FurryFriends_Backend.dto.UpdateUser;
import com.furryfriends.FurryFriends_Backend.entities.User;
import com.furryfriends.FurryFriends_Backend.enums.EnrollmentStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.furryfriends.FurryFriends_Backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // Permite solicitudes desde cualquier origen
public class UserController {

    @Autowired
    private UserService userService;

    // Obtener todos los usuarios
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Crear un nuevo usuario (mejor sin "/create")
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
        if (newUser != null) {
            return new ResponseEntity<>("Usuario creado exitosamente", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Error al crear el usuario", HttpStatus.BAD_REQUEST);
        }
    }

    // Actualizar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UpdateUser dto) {
        try {
            userService.updateUser(id, dto);
            return ResponseEntity.ok("Usuario actualizado exitosamente");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();  // Muy recomendable para saber el error exacto en consola
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar usuario");
        }
    }

    // Eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        return deleted
                ? new ResponseEntity<>("Usuario eliminado exitosamente", HttpStatus.OK)
                : new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
    }

    //Desactivar un usuario
    @PatchMapping("/{id}/enrollment-status")
    public ResponseEntity<?> updateEnrollmentStatus(@PathVariable Long id, @RequestBody Map<String, String> request) {
        String statusStr = request.get("enrollmentStatus");
        try {
            EnrollmentStatus newStatus = EnrollmentStatus.valueOf(statusStr);
            boolean updated = userService.updateEnrollmentStatus(id, newStatus);
            if (!updated) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
            }
            return ResponseEntity.ok("Estado actualizado");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Estado inválido: " + statusStr);
        }
    }
}
