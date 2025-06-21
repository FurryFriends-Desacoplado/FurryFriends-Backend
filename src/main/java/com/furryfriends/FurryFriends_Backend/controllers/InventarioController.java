package com.furryfriends.FurryFriends_Backend.controllers;

import com.furryfriends.FurryFriends_Backend.entities.Inventario;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IInventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventarios")
@CrossOrigin(origins = "*")
public class InventarioController {

    @Autowired
    private IInventarioService inventarioService;

    @GetMapping
    public ResponseEntity<List<Inventario>> getAllInventarios() {
        List<Inventario> inventarios = inventarioService.findAll();
        return new ResponseEntity<>(inventarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventario> getInventarioById(@PathVariable Long id) {
        Inventario inventario = inventarioService.findById(id);
        if (inventario != null) {
            return new ResponseEntity<>(inventario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> createInventario(@RequestBody Inventario inventario) {
        Boolean result = inventarioService.create(inventario);
        if (result) {
            return new ResponseEntity<>("Inventario created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create Inventario", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateInventario(@PathVariable Long id, @RequestBody Inventario inventario) {
        Inventario existingInventario = inventarioService.findById(id);
        if (existingInventario == null) {
            return new ResponseEntity<>("Inventario not found", HttpStatus.NOT_FOUND);
        }

        inventario.setId(id);
        Boolean result = inventarioService.update(inventario);

        if (result) {
            return new ResponseEntity<>("Inventario updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update Inventario", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInventario(@PathVariable Long id) {
        Inventario existingInventario = inventarioService.findById(id);
        if (existingInventario == null) {
            return new ResponseEntity<>("Inventario not found", HttpStatus.NOT_FOUND);
        }

        Boolean result = inventarioService.delete(existingInventario);

        if (result) {
            return new ResponseEntity<>("Inventario deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete Inventario", HttpStatus.BAD_REQUEST);
        }
    }
}
