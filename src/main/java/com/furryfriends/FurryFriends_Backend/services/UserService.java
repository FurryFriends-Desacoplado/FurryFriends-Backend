package com.furryfriends.FurryFriends_Backend.services;

import com.furryfriends.FurryFriends_Backend.entities.User;
import com.furryfriends.FurryFriends_Backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Obtener todos los usuarios
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Obtener un usuario por ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Crear un nuevo usuario
    public User createUser(User user) {
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());
        return userRepository.save(user);
    }

    // Actualizar un usuario existente
    public User updateUser(Long id, User userDetails) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setNombre(userDetails.getNombre());
            user.setApellido(userDetails.getApellido());
            user.setFechaNacimiento(userDetails.getFechaNacimiento());
            user.setTelefono(userDetails.getTelefono());
            user.setEmail(userDetails.getEmail());
            user.setTipoDocumento(userDetails.getTipoDocumento());
            user.setNumeroDocumento(userDetails.getNumeroDocumento());
            user.setPassword(userDetails.getPassword());
            user.setRole(userDetails.getRole());
            user.setDireccion(userDetails.getDireccion());
            user.setFotoPerfil(userDetails.getFotoPerfil());
            user.setExperiencia(userDetails.getExperiencia());
            user.setNumeroMascotas(userDetails.getNumeroMascotas());
            user.setUpdatedAt(Instant.now());
            return userRepository.save(user);
        }
        return null; // O lanzar una excepción si prefieres
    }

    // Eliminar un usuario
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
