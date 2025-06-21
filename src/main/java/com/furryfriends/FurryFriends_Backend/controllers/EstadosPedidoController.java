package com.furryfriends.FurryFriends_Backend.controllers;

import com.furryfriends.FurryFriends_Backend.entities.EstadosPedido;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IEstadosPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estados-pedidos")
@CrossOrigin(origins = "*")
public class EstadosPedidoController {

    @Autowired
    private IEstadosPedidoService estadosPedidoService;

    @GetMapping
    public ResponseEntity<List<EstadosPedido>> getAllEstadosPedidos() {
        List<EstadosPedido> estadosPedidos = estadosPedidoService.findAll();
        return new ResponseEntity<>(estadosPedidos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadosPedido> getEstadosPedidoById(@PathVariable Long id) {
        EstadosPedido estadosPedido = estadosPedidoService.findById(id);
        if (estadosPedido != null) {
            return new ResponseEntity<>(estadosPedido, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> createEstadosPedido(@RequestBody EstadosPedido estadosPedido) {
        Boolean result = estadosPedidoService.create(estadosPedido);
        if (result) {
            return new ResponseEntity<>("Estado Pedido created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create Estado Pedido", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEstadosPedido(@PathVariable Long id, @RequestBody EstadosPedido estadosPedido) {
        EstadosPedido existingEstadosPedido = estadosPedidoService.findById(id);
        if (existingEstadosPedido == null) {
            return new ResponseEntity<>("Estado Pedido not found", HttpStatus.NOT_FOUND);
        }

        estadosPedido.setId(id);
        Boolean result = estadosPedidoService.update(estadosPedido);

        if (result) {
            return new ResponseEntity<>("Estado Pedido updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update Estado Pedido", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEstadosPedido(@PathVariable Long id) {
        EstadosPedido existingEstadosPedido = estadosPedidoService.findById(id);
        if (existingEstadosPedido == null) {
            return new ResponseEntity<>("Estado Pedido not found", HttpStatus.NOT_FOUND);
        }

        Boolean result = estadosPedidoService.delete(existingEstadosPedido);

        if (result) {
            return new ResponseEntity<>("Estado Pedido deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete Estado Pedido", HttpStatus.BAD_REQUEST);
        }
    }
}
