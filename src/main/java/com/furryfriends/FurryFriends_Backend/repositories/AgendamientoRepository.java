package com.furryfriends.FurryFriends_Backend.repositories;

import com.furryfriends.FurryFriends_Backend.entities.Agendamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamientoRepository extends JpaRepository<Agendamiento, String> {
    // Aquí puedes agregar métodos adicionales si es necesario
}
