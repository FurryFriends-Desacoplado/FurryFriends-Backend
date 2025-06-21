package com.furryfriends.FurryFriends_Backend.services;

import com.furryfriends.FurryFriends_Backend.entities.EstadosPedido;
import com.furryfriends.FurryFriends_Backend.repositories.EstadosPedidoRepository;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IEstadosPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadosPedidoService implements IEstadosPedidoService {

    @Autowired
    private EstadosPedidoRepository estadosPedidoRepository;

    @Override
    public List<EstadosPedido> findAll() {
        return estadosPedidoRepository.findAll();
    }

    @Override
    public EstadosPedido findById(Long id) {
        Optional<EstadosPedido> estadoPedido = estadosPedidoRepository.findById(id);
        return estadoPedido.orElse(null);  // Devuelve null si no encuentra el estado del pedido
    }

    @Override
    public Boolean create(EstadosPedido estadosPedido) {
        try {
            estadosPedidoRepository.save(estadosPedido);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean update(EstadosPedido estadosPedido) {
        try {
            estadosPedidoRepository.save(estadosPedido);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean delete(EstadosPedido estadosPedido) {
        try {
            estadosPedidoRepository.delete(estadosPedido);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
