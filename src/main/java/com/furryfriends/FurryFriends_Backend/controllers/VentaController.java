package com.furryfriends.FurryFriends_Backend.controllers;

import com.furryfriends.FurryFriends_Backend.entities.Venta;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "*")
public class VentaController {

    @Autowired
    private IVentaService ventaService;

    @GetMapping
    public ResponseEntity<List<Venta>> getAllVentas() {
        List<Venta> ventas = ventaService.findAll();
        return new ResponseEntity<>(ventas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> getVentaById(@PathVariable Long id) {
        Venta venta = ventaService.findById(id);
        if (venta != null) {
            return new ResponseEntity<>(venta, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> createVenta(@RequestBody Venta venta) {
        Boolean result = ventaService.create(venta);
        if (result) {
            return new ResponseEntity<>("Venta created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create Venta", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateVenta(@PathVariable Long id, @RequestBody Venta venta) {
        Venta existingVenta = ventaService.findById(id);
        if (existingVenta == null) {
            return new ResponseEntity<>("Venta not found", HttpStatus.NOT_FOUND);
        }

        venta.setId(id);
        Boolean result = ventaService.update(venta);

        if (result) {
            return new ResponseEntity<>("Venta updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update Venta", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVenta(@PathVariable Long id) {
        Venta existingVenta = ventaService.findById(id);
        if (existingVenta == null) {
            return new ResponseEntity<>("Venta not found", HttpStatus.NOT_FOUND);
        }

        Boolean result = ventaService.delete(existingVenta);

        if (result) {
            return new ResponseEntity<>("Venta deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete Venta", HttpStatus.BAD_REQUEST);
        }
    }
}
