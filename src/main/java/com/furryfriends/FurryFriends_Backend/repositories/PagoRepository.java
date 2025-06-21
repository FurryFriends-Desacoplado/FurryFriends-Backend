package com.furryfriends.FurryFriends_Backend.repositories;

import com.furryfriends.FurryFriends_Backend.entities.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
  // Puedes agregar métodos adicionales si es necesario
}
