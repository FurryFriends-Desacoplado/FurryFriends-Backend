package com.furryfriends.FurryFriends_Backend.controllers;

import com.furryfriends.FurryFriends_Backend.entities.Mascota;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IMascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
@CrossOrigin(origins = "*")
public class MascotaController {

    @Autowired
    private IMascotaService mascotaService;

    @GetMapping
    public ResponseEntity<List<Mascota>> getAllMascotas() {
        List<Mascota> mascotas = mascotaService.findAll();
        return new ResponseEntity<>(mascotas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mascota> getMascotaById(@PathVariable Long id) {
        Mascota mascota = mascotaService.findById(id);
        if (mascota != null) {
            return new ResponseEntity<>(mascota, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> createMascota(@RequestBody Mascota mascota) {
        Boolean result = mascotaService.create(mascota);
        if (result) {
            return new ResponseEntity<>("Mascota creada exitosamente", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Error al crear la Mascota", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMascota(@PathVariable Long id, @RequestBody Mascota mascota) {
        Mascota existingMascota = mascotaService.findById(id);
        if (existingMascota == null) {
            return new ResponseEntity<>("Mascota no encontrada", HttpStatus.NOT_FOUND);
        }

        mascota.setId(id);
        Boolean result = mascotaService.update(mascota);

        if (result) {
            return new ResponseEntity<>("Mascota actualizada exitosamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error al actualizar la Mascota", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMascota(@PathVariable Long id) {
        Mascota existingMascota = mascotaService.findById(id);
        if (existingMascota == null) {
            return new ResponseEntity<>("Mascota no encontrada", HttpStatus.NOT_FOUND);
        }

        Boolean result = mascotaService.delete(existingMascota);

        if (result) {
            return new ResponseEntity<>("Mascota deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete Mascota", HttpStatus.BAD_REQUEST);
        }
    }
}
