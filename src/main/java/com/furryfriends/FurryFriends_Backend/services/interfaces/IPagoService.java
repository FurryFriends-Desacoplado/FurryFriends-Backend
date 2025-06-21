package com.furryfriends.FurryFriends_Backend.services.interfaces;

import com.furryfriends.FurryFriends_Backend.entities.Pago;

import java.util.List;

public interface IPagoService {

    List<Pago> findAll();

    Pago findById(Long id);

    Boolean create(Pago pago);

    Boolean update(Pago pago);

    Boolean delete(Pago pago);
}
