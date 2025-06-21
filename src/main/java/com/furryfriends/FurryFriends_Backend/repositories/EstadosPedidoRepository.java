package com.furryfriends.FurryFriends_Backend.repositories;

import com.furryfriends.FurryFriends_Backend.entities.EstadosPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadosPedidoRepository extends JpaRepository<EstadosPedido, Long> {
    // Aquí puedes agregar métodos adicionales si es necesario
}
