package com.furryfriends.FurryFriends_Backend.services;

import com.furryfriends.FurryFriends_Backend.entities.Venta;
import com.furryfriends.FurryFriends_Backend.repositories.VentaRepository;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    public List<Venta> findAll() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta findById(Long id) {
        Optional<Venta> venta = ventaRepository.findById(id);
        return venta.orElse(null);
    }

    @Override
    public Boolean create(Venta venta) {
        try {
            ventaRepository.save(venta);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean update(Venta venta) {
        try {
            ventaRepository.save(venta);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean delete(Venta venta) {
        try {
            ventaRepository.delete(venta);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
