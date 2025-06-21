package com.furryfriends.FurryFriends_Backend.controllers;

import com.furryfriends.FurryFriends_Backend.entities.MediosPago;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IMediosPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medios-pagos")
@CrossOrigin(origins = "*")
public class MediosPagoController {

    @Autowired
    private IMediosPagoService mediosPagoService;

    @GetMapping
    public ResponseEntity<List<MediosPago>> getAllMediosPagos() {
        List<MediosPago> mediosPagos = mediosPagoService.findAll();
        return new ResponseEntity<>(mediosPagos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MediosPago> getMediosPagoById(@PathVariable Long id) {
        MediosPago mediosPago = mediosPagoService.findById(id);
        if (mediosPago != null) {
            return new ResponseEntity<>(mediosPago, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> createMediosPago(@RequestBody MediosPago mediosPago) {
        Boolean result = mediosPagoService.create(mediosPago);
        if (result) {
            return new ResponseEntity<>("MediosPago created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create MediosPago", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMediosPago(@PathVariable Long id, @RequestBody MediosPago mediosPago) {
        MediosPago existingMediosPago = mediosPagoService.findById(id);
        if (existingMediosPago == null) {
            return new ResponseEntity<>("MediosPago not found", HttpStatus.NOT_FOUND);
        }

        mediosPago.setId(id);
        Boolean result = mediosPagoService.update(mediosPago);

        if (result) {
            return new ResponseEntity<>("MediosPago updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update MediosPago", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMediosPago(@PathVariable Long id) {
        MediosPago existingMediosPago = mediosPagoService.findById(id);
        if (existingMediosPago == null) {
            return new ResponseEntity<>("MediosPago not found", HttpStatus.NOT_FOUND);
        }

        Boolean result = mediosPagoService.delete(existingMediosPago);

        if (result) {
            return new ResponseEntity<>("MediosPago deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete MediosPago", HttpStatus.BAD_REQUEST);
        }
    }
}
