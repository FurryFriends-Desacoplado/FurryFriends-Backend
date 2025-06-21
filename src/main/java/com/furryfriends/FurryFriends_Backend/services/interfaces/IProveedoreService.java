package com.furryfriends.FurryFriends_Backend.services.interfaces;

import com.furryfriends.FurryFriends_Backend.entities.Proveedore;

import java.util.List;

public interface IProveedoreService {

    List<Proveedore> findAll();

    Proveedore findById(Long id);

    Boolean create(Proveedore proveedore);

    Boolean update(Proveedore proveedore);

    Boolean delete(Proveedore proveedore);
}
