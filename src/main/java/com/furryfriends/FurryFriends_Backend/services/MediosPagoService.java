package com.furryfriends.FurryFriends_Backend.services;

import com.furryfriends.FurryFriends_Backend.entities.MediosPago;
import com.furryfriends.FurryFriends_Backend.repositories.MediosPagoRepository;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IMediosPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MediosPagoService implements IMediosPagoService {

    @Autowired
    private MediosPagoRepository mediosPagoRepository;

    @Override
    public List<MediosPago> findAll() {
        return mediosPagoRepository.findAll();
    }

    @Override
    public MediosPago findById(Long id) {
        Optional<MediosPago> mediosPago = mediosPagoRepository.findById(id);
        return mediosPago.orElse(null);  // Devuelve null si no se encuentra
    }

    @Override
    public Boolean create(MediosPago mediosPago) {
        try {
            mediosPagoRepository.save(mediosPago);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean update(MediosPago mediosPago) {
        try {
            mediosPagoRepository.save(mediosPago);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean delete(MediosPago mediosPago) {
        try {
            mediosPagoRepository.delete(mediosPago);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
