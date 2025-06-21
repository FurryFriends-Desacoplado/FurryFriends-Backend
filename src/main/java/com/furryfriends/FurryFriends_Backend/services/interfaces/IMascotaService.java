package com.furryfriends.FurryFriends_Backend.services.interfaces;

import com.furryfriends.FurryFriends_Backend.entities.Mascota;

import java.util.List;

public interface IMascotaService {

    List<Mascota> findAll();

    Mascota findById(Long id);

    Boolean create(Mascota mascota);

    Boolean update(Mascota mascota);

    Boolean delete(Mascota mascota);
}
