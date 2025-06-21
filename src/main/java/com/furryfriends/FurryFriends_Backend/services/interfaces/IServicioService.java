package com.furryfriends.FurryFriends_Backend.services.interfaces;

import com.furryfriends.FurryFriends_Backend.entities.Servicio;

import java.util.List;

public interface IServicioService {

    List<Servicio> findAll();

    Servicio findById(Long id);

    Boolean create(Servicio servicio);

    Boolean update(Servicio servicio);

    Boolean delete(Servicio servicio);
}
