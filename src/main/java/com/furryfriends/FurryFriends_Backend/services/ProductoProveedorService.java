package com.furryfriends.FurryFriends_Backend.services;

import com.furryfriends.FurryFriends_Backend.entities.ProductoProveedor;
import com.furryfriends.FurryFriends_Backend.repositories.ProductoProveedorRepository;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IProductoProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoProveedorService implements IProductoProveedorService {

    @Autowired
    private ProductoProveedorRepository productoProveedorRepository;

    @Override
    public List<ProductoProveedor> findAll() {
        return productoProveedorRepository.findAll();
    }

    @Override
    public ProductoProveedor findById(Long id) {
        Optional<ProductoProveedor> productoProveedor = productoProveedorRepository.findById(id);
        return productoProveedor.orElse(null);
    }

    @Override
    public Boolean create(ProductoProveedor productoProveedor) {
        try {
            productoProveedorRepository.save(productoProveedor);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean update(ProductoProveedor productoProveedor) {
        try {
            productoProveedorRepository.save(productoProveedor);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean delete(ProductoProveedor productoProveedor) {
        try {
            productoProveedorRepository.delete(productoProveedor);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
