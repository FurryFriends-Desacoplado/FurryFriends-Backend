package com.furryfriends.FurryFriends_Backend.repositories;

import com.furryfriends.FurryFriends_Backend.entities.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    List<Mascota> findByUserId(Long userId);
}
