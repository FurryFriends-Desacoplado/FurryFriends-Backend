package com.furryfriends.FurryFriends_Backend.repositories;

import com.furryfriends.FurryFriends_Backend.entities.Proveedore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedoreRepository extends JpaRepository<Proveedore, Long> {
    // Puedes agregar métodos personalizados si es necesario
}
