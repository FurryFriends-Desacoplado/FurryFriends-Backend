package com.furryfriends.FurryFriends_Backend.services.interfaces;

import com.furryfriends.FurryFriends_Backend.dto.MascotaDTO;
import com.furryfriends.FurryFriends_Backend.entities.Mascota;
import com.furryfriends.FurryFriends_Backend.dto.UpdateMascota;
import com.furryfriends.FurryFriends_Backend.enums.StatusPet;

import java.util.List;

public interface IMascotaService {

    List<Mascota> findAll();

    Mascota findById(Long id);

    Boolean create(Mascota mascota);

    Mascota update(Long id, UpdateMascota dto);

    Boolean delete(Mascota mascota);

    Boolean updateStatus(Long id, StatusPet statusPet);

    Boolean save(Mascota mascota);

    Mascota registrarMascota(MascotaDTO mascotaDTO, Long usuarioId);
}
