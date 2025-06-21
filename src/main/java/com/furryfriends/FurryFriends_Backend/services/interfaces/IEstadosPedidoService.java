package com.furryfriends.FurryFriends_Backend.services.interfaces;

import com.furryfriends.FurryFriends_Backend.entities.EstadosPedido;

import java.util.List;

public interface IEstadosPedidoService {

    List<EstadosPedido> findAll();

    EstadosPedido findById(Long id);

    Boolean create(EstadosPedido estadosPedido);

    Boolean update(EstadosPedido estadosPedido);

    Boolean delete(EstadosPedido estadosPedido);
}
