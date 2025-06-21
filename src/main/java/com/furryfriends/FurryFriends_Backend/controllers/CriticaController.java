package com.furryfriends.FurryFriends_Backend.controllers;

import com.furryfriends.FurryFriends_Backend.entities.Critica;
import com.furryfriends.FurryFriends_Backend.services.interfaces.ICriticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/criticas")
@CrossOrigin(origins = "*")
public class CriticaController {

    @Autowired
    private ICriticaService criticaService;

    @GetMapping
    public ResponseEntity<List<Critica>> getAllCriticas() {
        List<Critica> criticas = criticaService.findAll();
        return new ResponseEntity<>(criticas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Critica> getCriticaById(@PathVariable Long id) {
        Critica critica = criticaService.findById(id);
        if (critica != null) {
            return new ResponseEntity<>(critica, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> createCritica(@RequestBody Critica critica) {
        Boolean result = criticaService.create(critica);
        if (result) {
            return new ResponseEntity<>("Critica created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create critica", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCritica(@PathVariable Long id, @RequestBody Critica critica) {
        Critica existingCritica = criticaService.findById(id);
        if (existingCritica == null) {
            return new ResponseEntity<>("Critica not found", HttpStatus.NOT_FOUND);
        }

        critica.setId(id);
        Boolean result = criticaService.update(critica);

        if (result) {
            return new ResponseEntity<>("Critica updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update critica", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCritica(@PathVariable Long id) {
        Critica existingCritica = criticaService.findById(id);
        if (existingCritica == null) {
            return new ResponseEntity<>("Critica not found", HttpStatus.NOT_FOUND);
        }

        Boolean result = criticaService.delete(existingCritica);

        if (result) {
            return new ResponseEntity<>("Critica deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete critica", HttpStatus.BAD_REQUEST);
        }
    }
}
