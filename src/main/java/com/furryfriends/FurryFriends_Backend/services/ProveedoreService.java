package com.furryfriends.FurryFriends_Backend.services;

import com.furryfriends.FurryFriends_Backend.entities.Proveedore;
import com.furryfriends.FurryFriends_Backend.repositories.ProveedoreRepository;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IProveedoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedoreService implements IProveedoreService {

    @Autowired
    private ProveedoreRepository proveedoreRepository;

    @Override
    public List<Proveedore> findAll() {
        return proveedoreRepository.findAll();
    }

    @Override
    public Proveedore findById(Long id) {
        Optional<Proveedore> proveedore = proveedoreRepository.findById(id);
        return proveedore.orElse(null);
    }

    @Override
    public Boolean create(Proveedore proveedore) {
        try {
            proveedoreRepository.save(proveedore);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean update(Proveedore proveedore) {
        try {
            proveedoreRepository.save(proveedore);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean delete(Proveedore proveedore) {
        try {
            proveedoreRepository.delete(proveedore);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
