package com.furryfriends.FurryFriends_Backend.services;

import com.furryfriends.FurryFriends_Backend.dto.UpdateUser;
import com.furryfriends.FurryFriends_Backend.entities.User;
import com.furryfriends.FurryFriends_Backend.enums.EnrollmentStatus;
import com.furryfriends.FurryFriends_Backend.repositories.UserRepository;
import com.furryfriends.FurryFriends_Backend.security.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;


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

    @Transactional
    public void updateUser(Long id, UpdateUser dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con id: " + id));

        // Imprime antes de modificar para ver el estado actual
        System.out.println("Antes update: enrollmentStatus = " + user.getEnrollmentStatus());

        user.setNombre(dto.getNombre());
        user.setApellido(dto.getApellido());
        user.setFechaNacimiento(dto.getFechaNacimiento());
        user.setTelefono(dto.getTelefono());
        user.setEmail(dto.getEmail());
        user.setDireccion(dto.getDireccion());
        user.setTipoDocumento(dto.getTipoDocumento());
        user.setNumeroDocumento(dto.getNumeroDocumento());
        user.setRole(dto.getRole());
        user.setFotoPerfil(dto.getFotoPerfil());
        user.setExperiencia(dto.getExperiencia());
        user.setNumeroMascotas(dto.getNumeroMascotas());

        // Imprime después de modificar para asegurarte que no cambió
        System.out.println("Después update: enrollmentStatus = " + user.getEnrollmentStatus());

        user.setUpdatedAt(Instant.now());

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
    }




    // Método corregido para actualizar enrollmentStatus usando cast explícito en la consulta
    @Transactional
    public boolean updateEnrollmentStatus(Long userId, EnrollmentStatus newStatus) {
        int updated = userRepository.updateEnrollmentStatus(userId, newStatus.name());
        return updated > 0;
    }


    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el email: " + username));
        return new CustomUserDetails(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
