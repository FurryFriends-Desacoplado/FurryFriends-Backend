package com.furryfriends.FurryFriends_Backend.services.interfaces;

import com.furryfriends.FurryFriends_Backend.entities.Venta;

import java.util.List;

public interface IVentaService {

    List<Venta> findAll();

    Venta findById(Long id);

    Boolean create(Venta venta);

    Boolean update(Venta venta);

    Boolean delete(Venta venta);
}
