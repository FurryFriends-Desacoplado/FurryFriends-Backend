package com.furryfriends.FurryFriends_Backend.services.interfaces;

import com.furryfriends.FurryFriends_Backend.entities.OrdenCompra;

import java.util.List;

public interface IOrdenCompraService {

    List<OrdenCompra> findAll();

    OrdenCompra findById(Long id);

    Boolean create(OrdenCompra ordenCompra);

    Boolean update(OrdenCompra ordenCompra);

    Boolean delete(OrdenCompra ordenCompra);
}
