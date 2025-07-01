package com.furryfriends.FurryFriends_Backend.services;

import com.furryfriends.FurryFriends_Backend.entities.Producto;
import com.furryfriends.FurryFriends_Backend.enums.ProductStatus;
import com.furryfriends.FurryFriends_Backend.repositories.ProductoRepository;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
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
            Instant now = Instant.now();
            producto.setCreatedAt(now);
            producto.setUpdatedAt(now);
            productoRepository.save(producto);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al guardar producto: " + e.getMessage(), e); // Lanza con detalle
        }
    }

    @Override
    public Boolean update(Producto producto) {
        try {
            producto.setUpdatedAt(Instant.now()); // ← ¡Aquí actualizamos!
            productoRepository.save(producto);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
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

    @Override
    public Boolean updateProductStatus(Long id, ProductStatus newStatus) {
        Optional<Producto> optionalProducto = productoRepository.findById(id);
        if (optionalProducto.isPresent()) {
            Producto producto = optionalProducto.get();
            producto.setProductStatus(newStatus);
            producto.setUpdatedAt(Instant.now());
            productoRepository.save(producto);
            return true;
        }
        return false;
    }
}
