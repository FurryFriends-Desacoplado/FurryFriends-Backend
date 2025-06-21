package com.furryfriends.FurryFriends_Backend.services;

import com.furryfriends.FurryFriends_Backend.entities.Pago;
import com.furryfriends.FurryFriends_Backend.repositories.PagoRepository;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService implements IPagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Override
    public List<Pago> findAll() {
        return pagoRepository.findAll();
    }

    @Override
    public Pago findById(Long id) {
        Optional<Pago> pago = pagoRepository.findById(id);
        return pago.orElse(null); // Retorna null si no se encuentra
    }

    @Override
    public Boolean create(Pago pago) {
        try {
            pagoRepository.save(pago);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean update(Pago pago) {
        try {
            pagoRepository.save(pago);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean delete(Pago pago) {
        try {
            pagoRepository.delete(pago);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
