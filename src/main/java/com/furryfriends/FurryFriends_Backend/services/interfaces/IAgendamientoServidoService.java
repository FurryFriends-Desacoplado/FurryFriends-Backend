package com.furryfriends.FurryFriends_Backend.services.interfaces;

import com.furryfriends.FurryFriends_Backend.entities.AgendamientoServido;

import java.util.List;

public interface IAgendamientoServidoService {

    List<AgendamientoServido> findAll();

    AgendamientoServido findById(Long id);

    Boolean create(AgendamientoServido agendamientoServido);

    Boolean update(AgendamientoServido agendamientoServido);

    Boolean delete(AgendamientoServido agendamientoServido);
}
