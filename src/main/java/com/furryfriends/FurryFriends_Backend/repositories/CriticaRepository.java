package com.furryfriends.FurryFriends_Backend.repositories;

import com.furryfriends.FurryFriends_Backend.entities.Critica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriticaRepository extends JpaRepository<Critica, Long> {
    // Aquí puedes agregar métodos adicionales si es necesario
}
