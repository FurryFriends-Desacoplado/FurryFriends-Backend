package com.furryfriends.FurryFriends_Backend.services;

import com.furryfriends.FurryFriends_Backend.entities.OrdenCompra;
import com.furryfriends.FurryFriends_Backend.repositories.OrdenCompraRepository;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IOrdenCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenCompraService implements IOrdenCompraService {

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    @Override
    public List<OrdenCompra> findAll() {
        return ordenCompraRepository.findAll();
    }

    @Override
    public OrdenCompra findById(Long id) {
        Optional<OrdenCompra> ordenCompra = ordenCompraRepository.findById(id);
        return ordenCompra.orElse(null); // Retorna null si no se encuentra
    }

    @Override
    public Boolean create(OrdenCompra ordenCompra) {
        try {
            ordenCompraRepository.save(ordenCompra);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean update(OrdenCompra ordenCompra) {
        try {
            ordenCompraRepository.save(ordenCompra);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean delete(OrdenCompra ordenCompra) {
        try {
            ordenCompraRepository.delete(ordenCompra);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
