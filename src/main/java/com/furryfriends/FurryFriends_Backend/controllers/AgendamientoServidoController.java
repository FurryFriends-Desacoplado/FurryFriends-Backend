package com.furryfriends.FurryFriends_Backend.controllers;

import com.furryfriends.FurryFriends_Backend.entities.AgendamientoServido;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IAgendamientoServidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendamientos-servidos")
@CrossOrigin(origins = "*")
public class AgendamientoServidoController {

    @Autowired
    private IAgendamientoServidoService agendamientoServidoService;

    @GetMapping
    public ResponseEntity<List<AgendamientoServido>> getAllAgendamientosServidos() {
        List<AgendamientoServido> agendamientosServidos = agendamientoServidoService.findAll();
        return new ResponseEntity<>(agendamientosServidos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamientoServido> getAgendamientoServidoById(@PathVariable Long id) {
        AgendamientoServido agendamientoServido = agendamientoServidoService.findById(id);
        if (agendamientoServido != null) {
            return new ResponseEntity<>(agendamientoServido, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> createAgendamientoServido(@RequestBody AgendamientoServido agendamientoServido) {
        Boolean result = agendamientoServidoService.create(agendamientoServido);
        if (result) {
            return new ResponseEntity<>("Agendamiento Servido created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create agendamiento servido", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAgendamientoServido(@PathVariable Long id, @RequestBody AgendamientoServido agendamientoServido) {
        AgendamientoServido existingAgendamientoServido = agendamientoServidoService.findById(id);
        if (existingAgendamientoServido == null) {
            return new ResponseEntity<>("Agendamiento Servido not found", HttpStatus.NOT_FOUND);
        }

        agendamientoServido.setId(id);
        Boolean result = agendamientoServidoService.update(agendamientoServido);

        if (result) {
            return new ResponseEntity<>("Agendamiento Servido updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update agendamiento servido", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAgendamientoServido(@PathVariable Long id) {
        AgendamientoServido existingAgendamientoServido = agendamientoServidoService.findById(id);
        if (existingAgendamientoServido == null) {
            return new ResponseEntity<>("Agendamiento Servido not found", HttpStatus.NOT_FOUND);
        }

        Boolean result = agendamientoServidoService.delete(existingAgendamientoServido);

        if (result) {
            return new ResponseEntity<>("Agendamiento Servido deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete agendamiento servido", HttpStatus.BAD_REQUEST);
        }
    }
}
