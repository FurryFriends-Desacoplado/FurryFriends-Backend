package com.furryfriends.FurryFriends_Backend.controllers;

import com.furryfriends.FurryFriends_Backend.entities.OrdenCompra;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IOrdenCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orden-compras")
@CrossOrigin(origins = "*")
public class OrdenCompraController {

    @Autowired
    private IOrdenCompraService ordenCompraService;

    @GetMapping
    public ResponseEntity<List<OrdenCompra>> getAllOrdenCompras() {
        List<OrdenCompra> ordenCompras = ordenCompraService.findAll();
        return new ResponseEntity<>(ordenCompras, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenCompra> getOrdenCompraById(@PathVariable Long id) {
        OrdenCompra ordenCompra = ordenCompraService.findById(id);
        if (ordenCompra != null) {
            return new ResponseEntity<>(ordenCompra, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> createOrdenCompra(@RequestBody OrdenCompra ordenCompra) {
        Boolean result = ordenCompraService.create(ordenCompra);
        if (result) {
            return new ResponseEntity<>("OrdenCompra created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create OrdenCompra", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrdenCompra(@PathVariable Long id, @RequestBody OrdenCompra ordenCompra) {
        OrdenCompra existingOrdenCompra = ordenCompraService.findById(id);
        if (existingOrdenCompra == null) {
            return new ResponseEntity<>("OrdenCompra not found", HttpStatus.NOT_FOUND);
        }

        ordenCompra.setId(id);
        Boolean result = ordenCompraService.update(ordenCompra);

        if (result) {
            return new ResponseEntity<>("OrdenCompra updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update OrdenCompra", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrdenCompra(@PathVariable Long id) {
        OrdenCompra existingOrdenCompra = ordenCompraService.findById(id);
        if (existingOrdenCompra == null) {
            return new ResponseEntity<>("OrdenCompra not found", HttpStatus.NOT_FOUND);
        }

        Boolean result = ordenCompraService.delete(existingOrdenCompra);

        if (result) {
            return new ResponseEntity<>("OrdenCompra deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete OrdenCompra", HttpStatus.BAD_REQUEST);
        }
    }
}
