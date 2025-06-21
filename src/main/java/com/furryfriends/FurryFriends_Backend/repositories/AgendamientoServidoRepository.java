package com.furryfriends.FurryFriends_Backend.repositories;

import com.furryfriends.FurryFriends_Backend.entities.AgendamientoServido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamientoServidoRepository extends JpaRepository<AgendamientoServido, Long> {
  // Aquí puedes agregar métodos adicionales si es necesario
}
