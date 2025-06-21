package com.furryfriends.FurryFriends_Backend.controllers;

import com.furryfriends.FurryFriends_Backend.entities.Servicio;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
@CrossOrigin(origins = "*")
public class ServicioController {

    @Autowired
    private IServicioService servicioService;

    @GetMapping
    public ResponseEntity<List<Servicio>> getAllServicios() {
        List<Servicio> servicios = servicioService.findAll();
        return new ResponseEntity<>(servicios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servicio> getServicioById(@PathVariable Long id) {
        Servicio servicio = servicioService.findById(id);
        if (servicio != null) {
            return new ResponseEntity<>(servicio, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> createServicio(@RequestBody Servicio servicio) {
        Boolean result = servicioService.create(servicio);
        if (result) {
            return new ResponseEntity<>("Servicio created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create Servicio", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateServicio(@PathVariable Long id, @RequestBody Servicio servicio) {
        Servicio existingServicio = servicioService.findById(id);
        if (existingServicio == null) {
            return new ResponseEntity<>("Servicio not found", HttpStatus.NOT_FOUND);
        }

        servicio.setId(id);
        Boolean result = servicioService.update(servicio);

        if (result) {
            return new ResponseEntity<>("Servicio updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update Servicio", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteServicio(@PathVariable Long id) {
        Servicio existingServicio = servicioService.findById(id);
        if (existingServicio == null) {
            return new ResponseEntity<>("Servicio not found", HttpStatus.NOT_FOUND);
        }

        Boolean result = servicioService.delete(existingServicio);

        if (result) {
            return new ResponseEntity<>("Servicio deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete Servicio", HttpStatus.BAD_REQUEST);
        }
    }
}
