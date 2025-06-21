package com.furryfriends.FurryFriends_Backend.services.interfaces;

import com.furryfriends.FurryFriends_Backend.entities.ProductoProveedor;

import java.util.List;

public interface IProductoProveedorService {

    List<ProductoProveedor> findAll();

    ProductoProveedor findById(Long id);

    Boolean create(ProductoProveedor productoProveedor);

    Boolean update(ProductoProveedor productoProveedor);

    Boolean delete(ProductoProveedor productoProveedor);
}
