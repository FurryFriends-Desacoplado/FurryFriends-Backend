package com.furryfriends.FurryFriends_Backend.repositories;

import com.furryfriends.FurryFriends_Backend.entities.MediosPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediosPagoRepository extends JpaRepository<MediosPago, Long> {
  // Puedes agregar métodos adicionales si es necesario
}
