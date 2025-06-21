package com.furryfriends.FurryFriends_Backend.services.interfaces;

import com.furryfriends.FurryFriends_Backend.entities.Inventario;

import java.util.List;

public interface IInventarioService {

    List<Inventario> findAll();

    Inventario findById(Long id);

    Boolean create(Inventario inventario);

    Boolean update(Inventario inventario);

    Boolean delete(Inventario inventario);
}
