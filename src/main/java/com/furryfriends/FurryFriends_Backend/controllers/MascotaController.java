package com.furryfriends.FurryFriends_Backend.controllers;

import com.furryfriends.FurryFriends_Backend.dto.MascotaDTO;
import com.furryfriends.FurryFriends_Backend.dto.UpdateMascota;
import com.furryfriends.FurryFriends_Backend.entities.Mascota;
import com.furryfriends.FurryFriends_Backend.enums.StatusPet;
import com.furryfriends.FurryFriends_Backend.mappers.MascotaMapper;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IMascotaService;
import com.furryfriends.FurryFriends_Backend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mascotas")
@CrossOrigin(origins = "*")
public class MascotaController {

    @Autowired
    private IMascotaService mascotaService;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping
    public ResponseEntity<List<MascotaDTO>> getAllMascotas() {
        List<Mascota> mascotas = mascotaService.findAll();
        List<MascotaDTO> mascotaDtos = mascotas.stream()
                .map(MascotaMapper::toDto)
                .toList();
        return new ResponseEntity<>(mascotaDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MascotaDTO> getMascotaById(@PathVariable Long id) {
        Mascota mascota = mascotaService.findById(id);
        if (mascota != null) {
            return new ResponseEntity<>(MascotaMapper.toDto(mascota), HttpStatus.OK);
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
    public ResponseEntity<Mascota> updateMascota(@PathVariable Long id, @RequestBody UpdateMascota update) {
        try {
            Mascota mascotaActualizada = mascotaService.update(id, update);
            return ResponseEntity.ok(mascotaActualizada);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PatchMapping("/{id}/status-pet")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        try {
            String statusPetString = body.get("statusPet");
            System.out.println("Estado recibido: " + statusPetString);

            if (statusPetString == null || statusPetString.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El estado de la mascota es obligatorio.");
            }

            if ("Activa".equalsIgnoreCase(statusPetString) || "Inactiva".equalsIgnoreCase(statusPetString)) {
                StatusPet statusPet = StatusPet.valueOf(statusPetString);

                System.out.println("Estado convertido a enum: " + statusPet);

                Mascota mascota = mascotaService.findById(id);
                mascota.setStatusPet(statusPet);
                mascotaService.save(mascota);

                return ResponseEntity.ok(mascota);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Estado de mascota inválido.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
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
            return new ResponseEntity<>("Mascota eliminada exitosamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error al eliminar la mascota", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/registerMascotas")
    public ResponseEntity<String> registrarMascota(@RequestBody MascotaDTO mascotaDTO,
                                                   @RequestHeader("Authorization") String token) {
        try {
            System.out.println("🔐 Token recibido en el backend:");
            System.out.println(token);

            // ✅ Elimina el prefijo "Bearer " si está presente
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            Long userId = jwtUtils.getUserIdFromToken(token);
            if (userId == null) {
                throw new RuntimeException("No se pudo extraer el ID del token");
            }

            mascotaService.registrarMascota(mascotaDTO, userId);
            return ResponseEntity.ok("Mascota registrada exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al registrar mascota: " + e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Mascota>> getMascotasByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(mascotaService.findByUserId(userId));
    }

}
