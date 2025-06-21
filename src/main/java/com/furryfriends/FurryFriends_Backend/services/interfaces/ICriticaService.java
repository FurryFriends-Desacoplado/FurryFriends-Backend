package com.furryfriends.FurryFriends_Backend.services.interfaces;

import com.furryfriends.FurryFriends_Backend.entities.Critica;

import java.util.List;

public interface ICriticaService {

    List<Critica> findAll();

    Critica findById(Long id);

    Boolean create(Critica critica);

    Boolean update(Critica critica);

    Boolean delete(Critica critica);
}
