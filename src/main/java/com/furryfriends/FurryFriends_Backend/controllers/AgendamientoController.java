package com.furryfriends.FurryFriends_Backend.controllers;

import com.furryfriends.FurryFriends_Backend.entities.Agendamiento;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IAgendamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendamientos")
@CrossOrigin(origins = "*")
public class AgendamientoController {

    @Autowired
    private IAgendamientoService agendamientoService;

    @GetMapping
    public ResponseEntity<List<Agendamiento>> getAllAgendamientos() {
        List<Agendamiento> agendamientos = agendamientoService.findAll();
        return new ResponseEntity<>(agendamientos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamiento> getAgendamientoById(@PathVariable String id) {
        Agendamiento agendamiento = agendamientoService.findById(id);
        if (agendamiento != null) {
            return new ResponseEntity<>(agendamiento, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> createAgendamiento(@RequestBody Agendamiento agendamiento) {
        Boolean result = agendamientoService.create(agendamiento);
        if (result) {
            return new ResponseEntity<>("Agendamiento created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create agendamiento", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAgendamiento(@PathVariable String id, @RequestBody Agendamiento agendamiento) {
        Agendamiento existingAgendamiento = agendamientoService.findById(id);
        if (existingAgendamiento == null) {
            return new ResponseEntity<>("Agendamiento not found", HttpStatus.NOT_FOUND);
        }

        agendamiento.setId(id);
        Boolean result = agendamientoService.update(agendamiento);

        if (result) {
            return new ResponseEntity<>("Agendamiento updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update agendamiento", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAgendamiento(@PathVariable String id) {
        Agendamiento existingAgendamiento = agendamientoService.findById(id);
        if (existingAgendamiento == null) {
            return new ResponseEntity<>("Agendamiento not found", HttpStatus.NOT_FOUND);
        }

        Boolean result = agendamientoService.delete(existingAgendamiento);

        if (result) {
            return new ResponseEntity<>("Agendamiento deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete agendamiento", HttpStatus.BAD_REQUEST);
        }
    }
}
