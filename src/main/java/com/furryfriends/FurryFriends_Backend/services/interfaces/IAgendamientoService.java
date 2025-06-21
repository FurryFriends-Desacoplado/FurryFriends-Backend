package com.furryfriends.FurryFriends_Backend.services.interfaces;

import com.furryfriends.FurryFriends_Backend.entities.Agendamiento;

import java.util.List;

public interface IAgendamientoService {

    List<Agendamiento> findAll();

    Agendamiento findById(String id);

    Boolean create(Agendamiento agendamiento);

    Boolean update(Agendamiento agendamiento);

    Boolean delete(Agendamiento agendamiento);
}
