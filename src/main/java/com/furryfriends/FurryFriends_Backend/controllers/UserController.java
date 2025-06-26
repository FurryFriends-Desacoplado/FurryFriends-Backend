package com.furryfriends.FurryFriends_Backend.controllers;

import com.furryfriends.FurryFriends_Backend.entities.User;
import com.furryfriends.FurryFriends_Backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")  // Permite solicitudes desde cualquier origen
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
        User user = userService.getUserById(id).orElse(null);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Si no lo encuentra
        }
    }

    // Crear un nuevo usuario
    @PostMapping("/create")
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
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        if (updatedUser != null) {
            return new ResponseEntity<>("Usuario actualizado exitosamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        User existingUser = userService.getUserById(id).orElse(null);
        if (existingUser == null) {
            return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
        }

        userService.deleteUser(id);
        return new ResponseEntity<>("Usuario eliminado exitosamente", HttpStatus.OK);
    }
}