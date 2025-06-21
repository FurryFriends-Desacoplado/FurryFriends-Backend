package com.furryfriends.FurryFriends_Backend.controllers;

import com.furryfriends.FurryFriends_Backend.entities.Pago;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin(origins = "*")
public class PagoController {

    @Autowired
    private IPagoService pagoService;

    @GetMapping
    public ResponseEntity<List<Pago>> getAllPagos() {
        List<Pago> pagos = pagoService.findAll();
        return new ResponseEntity<>(pagos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> getPagoById(@PathVariable Long id) {
        Pago pago = pagoService.findById(id);
        if (pago != null) {
            return new ResponseEntity<>(pago, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> createPago(@RequestBody Pago pago) {
        Boolean result = pagoService.create(pago);
        if (result) {
            return new ResponseEntity<>("Pago created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create pago", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePago(@PathVariable Long id, @RequestBody Pago pago) {
        Pago existingPago = pagoService.findById(id);
        if (existingPago == null) {
            return new ResponseEntity<>("Pago not found", HttpStatus.NOT_FOUND);
        }

        pago.setId(id);
        Boolean result = pagoService.update(pago);

        if (result) {
            return new ResponseEntity<>("Pago updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update pago", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePago(@PathVariable Long id) {
        Pago existingPago = pagoService.findById(id);
        if (existingPago == null) {
            return new ResponseEntity<>("Pago not found", HttpStatus.NOT_FOUND);
        }

        Boolean result = pagoService.delete(existingPago);

        if (result) {
            return new ResponseEntity<>("Pago deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete pago", HttpStatus.BAD_REQUEST);
        }
    }
}
