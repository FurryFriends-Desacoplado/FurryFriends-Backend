package com.furryfriends.FurryFriends_Backend.services.interfaces;

import com.furryfriends.FurryFriends_Backend.entities.Producto;
import com.furryfriends.FurryFriends_Backend.enums.ProductStatus;

import java.util.List;

public interface IProductoService {

    List<Producto> findAll();

    Producto findById(Long id);

    Boolean create(Producto producto);

    Boolean update(Producto producto);

    Boolean delete(Producto producto);

    Boolean updateProductStatus(Long id, ProductStatus newStatus);

}
