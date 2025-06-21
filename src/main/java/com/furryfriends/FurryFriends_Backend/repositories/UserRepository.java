package com.furryfriends.FurryFriends_Backend.repositories;

import com.furryfriends.FurryFriends_Backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Puedes agregar métodos adicionales de consulta si lo necesitas, como:
    Optional<User> findByEmail(String email);

    Optional<User> findByNumeroDocumento(String numeroDocumento);
}
