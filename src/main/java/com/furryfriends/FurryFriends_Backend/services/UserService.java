package com.furryfriends.FurryFriends_Backend.services;

import com.furryfriends.FurryFriends_Backend.entities.User;
import com.furryfriends.FurryFriends_Backend.repositories.UserRepository;
import com.furryfriends.FurryFriends_Backend.security.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());
        return userRepository.save(user);
    }

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
            if (userDetails.getPassword() != null && !userDetails.getPassword().isBlank()) {
                user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
            }
            user.setRole(userDetails.getRole());
            user.setDireccion(userDetails.getDireccion());
            user.setFotoPerfil(userDetails.getFotoPerfil());
            user.setExperiencia(userDetails.getExperiencia());
            user.setNumeroMascotas(userDetails.getNumeroMascotas());
            user.setUpdatedAt(Instant.now());
            return userRepository.save(user);
        }
        throw new UsernameNotFoundException("Usuario no encontrado con id: " + id);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Método agregado para validar existencia de usuario por email
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el email: " + username));

        return new CustomUserDetails(user);
    }
}
