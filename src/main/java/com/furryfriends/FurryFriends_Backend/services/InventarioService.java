package com.furryfriends.FurryFriends_Backend.services;

import com.furryfriends.FurryFriends_Backend.entities.Inventario;
import com.furryfriends.FurryFriends_Backend.repositories.InventarioRepository;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IInventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService implements IInventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Override
    public List<Inventario> findAll() {
        return inventarioRepository.findAll();
    }

    @Override
    public Inventario findById(Long id) {
        Optional<Inventario> inventario = inventarioRepository.findById(id);
        return inventario.orElse(null);  // Devuelve null si no encuentra el inventario
    }

    @Override
    public Boolean create(Inventario inventario) {
        try {
            inventarioRepository.save(inventario);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean update(Inventario inventario) {
        try {
            inventarioRepository.save(inventario);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean delete(Inventario inventario) {
        try {
            inventarioRepository.delete(inventario);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
