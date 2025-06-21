package com.furryfriends.FurryFriends_Backend.services.interfaces;

import com.furryfriends.FurryFriends_Backend.entities.MediosPago;

import java.util.List;

public interface IMediosPagoService {

    List<MediosPago> findAll();

    MediosPago findById(Long id);

    Boolean create(MediosPago mediosPago);

    Boolean update(MediosPago mediosPago);

    Boolean delete(MediosPago mediosPago);
}
