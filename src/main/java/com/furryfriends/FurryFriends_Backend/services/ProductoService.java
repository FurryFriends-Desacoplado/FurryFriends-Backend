package com.furryfriends.FurryFriends_Backend.services;

import com.furryfriends.FurryFriends_Backend.entities.Producto;
import com.furryfriends.FurryFriends_Backend.repositories.ProductoRepository;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Producto findById(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.orElse(null);  // Devuelve null si no encuentra el producto
    }

    @Override
    public Boolean create(Producto producto) {
        try {
            productoRepository.save(producto);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean update(Producto producto) {
        try {
            productoRepository.save(producto);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean delete(Producto producto) {
        try {
            productoRepository.delete(producto);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
