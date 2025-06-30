package com.furryfriends.FurryFriends_Backend.controllers;

import com.furryfriends.FurryFriends_Backend.dto.MascotaDTO;
import com.furryfriends.FurryFriends_Backend.dto.UpdateMascota;
import com.furryfriends.FurryFriends_Backend.entities.Mascota;
import com.furryfriends.FurryFriends_Backend.enums.StatusPet;
import com.furryfriends.FurryFriends_Backend.mappers.MascotaMapper;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IMascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.nio.charset.StandardCharsets;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mascotas")
@CrossOrigin(origins = "*")
public class MascotaController {

    @Autowired
    private IMascotaService mascotaService;

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
            // Obtener el valor de "statusPet" desde el cuerpo de la solicitud
            String statusPetString = body.get("statusPet");
            System.out.println("Estado recibido: " + statusPetString);  // Log para verificar el valor

            if (statusPetString == null || statusPetString.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El estado de la mascota es obligatorio.");
            }

            // Verificar si el valor recibido es válido
            if ("Activa".equalsIgnoreCase(statusPetString) || "Inactiva".equalsIgnoreCase(statusPetString)) {
                // Si es válido, procesamos el estado
                StatusPet statusPet = StatusPet.valueOf(statusPetString);  // Aquí no es necesario convertir a mayúsculas, ya que está en el formato correcto.

                System.out.println("Estado convertido a enum: " + statusPet);  // Log de la conversión

                // Buscar la mascota por ID
                Mascota mascota = mascotaService.findById(id);

                // Actualizar el estado de la mascota
                mascota.setStatusPet(statusPet);

                // Guardar la mascota con el nuevo estado
                mascotaService.save(mascota);

                return ResponseEntity.ok(mascota);  // Responde con la mascota actualizada
            } else {
                // Si el estado no es válido, devolver un error 400
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Estado de mascota inválido.");
            }
        } catch (Exception e) {
            // Manejo de errores generales
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
        // Extraer el userId del token
        Long usuarioId = getUserIdFromToken(token);

        try {
            mascotaService.registrarMascota(mascotaDTO, usuarioId);
            return ResponseEntity.ok("Mascota registrada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar mascota: " + e.getMessage());
        }
    }

    private Long getUserIdFromToken(String token) {
        // Eliminar "Bearer " si está presente
        token = token.replace("Bearer ", "");

        // Parsear el token
        Claims claims = Jwts.parser()
                .setSigningKey("your-secret-key".getBytes(StandardCharsets.UTF_8)) // Tu clave secreta para firmar el JWT
                .parseClaimsJws(token)
                .getBody();

        // Obtener el `userId` que está como `subject` en el token
        return Long.parseLong(claims.getSubject()); // Suponiendo que el `subject` es el `userId`
    }
}
