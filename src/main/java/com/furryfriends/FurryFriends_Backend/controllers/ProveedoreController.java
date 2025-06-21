package com.furryfriends.FurryFriends_Backend.controllers;

import com.furryfriends.FurryFriends_Backend.entities.Proveedore;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IProveedoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@CrossOrigin(origins = "*")
public class ProveedoreController {

    @Autowired
    private IProveedoreService proveedoreService;

    @GetMapping
    public ResponseEntity<List<Proveedore>> getAllProveedores() {
        List<Proveedore> proveedores = proveedoreService.findAll();
        return new ResponseEntity<>(proveedores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedore> getProveedoreById(@PathVariable Long id) {
        Proveedore proveedore = proveedoreService.findById(id);
        if (proveedore != null) {
            return new ResponseEntity<>(proveedore, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> createProveedore(@RequestBody Proveedore proveedore) {
        Boolean result = proveedoreService.create(proveedore);
        if (result) {
            return new ResponseEntity<>("Proveedore created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create Proveedore", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProveedore(@PathVariable Long id, @RequestBody Proveedore proveedore) {
        Proveedore existingProveedore = proveedoreService.findById(id);
        if (existingProveedore == null) {
            return new ResponseEntity<>("Proveedore not found", HttpStatus.NOT_FOUND);
        }

        proveedore.setId(id);
        Boolean result = proveedoreService.update(proveedore);

        if (result) {
            return new ResponseEntity<>("Proveedore updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update Proveedore", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProveedore(@PathVariable Long id) {
        Proveedore existingProveedore = proveedoreService.findById(id);
        if (existingProveedore == null) {
            return new ResponseEntity<>("Proveedore not found", HttpStatus.NOT_FOUND);
        }

        Boolean result = proveedoreService.delete(existingProveedore);

        if (result) {
            return new ResponseEntity<>("Proveedore deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete Proveedore", HttpStatus.BAD_REQUEST);
        }
    }
}
