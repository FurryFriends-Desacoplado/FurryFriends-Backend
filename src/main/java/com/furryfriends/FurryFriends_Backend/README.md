# FurryFriends Backend - Repository, Service, and Controller Implementation Guide

This guide explains how to create repositories, services, and controllers for all entities in the FurryFriends Backend project.

## Project Structure

The project follows a standard Spring Boot architecture with the following layers:

- **Entities**: JPA entities representing database tables
- **Repositories**: Spring Data JPA repositories for data access
- **Services**: Business logic layer with interfaces and implementations
- **Controllers**: REST controllers for API endpoints

## Implementation Steps for Each Entity

Follow these steps to create the necessary components for each entity:

### 1. Create Repository Interface

```java
package com.furryfriends.FurryFriends_Backend.repositories;

import com.furryfriends.FurryFriends_Backend.entities.EntityName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityNameRepository extends JpaRepository<EntityName, Long> {
    // Add custom query methods if needed
}
```

### 2. Create Service Interface

```java
package com.furryfriends.FurryFriends_Backend.services.interfaces;

import com.furryfriends.FurryFriends_Backend.entities.EntityName;
import com.furryfriends.FurryFriends_Backend.services.dao.Idao;

public interface IEntityNameService extends Idao<EntityName, Long> {
    // Add additional methods specific to this entity if needed
}
```

### 3. Create Service Implementation

```java
package com.furryfriends.FurryFriends_Backend.services.implementations;

import com.furryfriends.FurryFriends_Backend.entities.EntityName;
import com.furryfriends.FurryFriends_Backend.repositories.EntityNameRepository;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IEntityNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class EntityNameService implements IEntityNameService {

    @Autowired
    private EntityNameRepository entityNameRepository;

    @Override
    public List<EntityName> findAll() {
        return entityNameRepository.findAll();
    }

    @Override
    public EntityName findById(Long id) {
        Optional<EntityName> optionalEntity = entityNameRepository.findById(id);
        return optionalEntity.orElse(null);
    }

    @Override
    public Boolean create(EntityName entity) {
        try {
            // Set timestamps if the entity has them
            if (entity.getClass().getDeclaredField("createdAt") != null) {
                entity.setCreatedAt(Instant.now());
            }
            if (entity.getClass().getDeclaredField("updatedAt") != null) {
                entity.setUpdatedAt(Instant.now());
            }
            entityNameRepository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean update(EntityName entity) {
        try {
            // Set updated timestamp if the entity has it
            if (entity.getClass().getDeclaredField("updatedAt") != null) {
                entity.setUpdatedAt(Instant.now());
            }
            entityNameRepository.save(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean delete(EntityName entity) {
        try {
            entityNameRepository.delete(entity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean show(EntityName entity) {
        // Implementation depends on business requirements
        return true;
    }
}
```

### 4. Create Controller

```java
package com.furryfriends.FurryFriends_Backend.controllers;

import com.furryfriends.FurryFriends_Backend.entities.EntityName;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IEntityNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entitynames")
@CrossOrigin(origins = "*")
public class EntityNameController {

    @Autowired
    private IEntityNameService entityNameService;

    @GetMapping
    public ResponseEntity<List<EntityName>> getAllEntities() {
        List<EntityName> entities = entityNameService.findAll();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityName> getEntityById(@PathVariable Long id) {
        EntityName entity = entityNameService.findById(id);
        if (entity != null) {
            return new ResponseEntity<>(entity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> createEntity(@RequestBody EntityName entity) {
        Boolean result = entityNameService.create(entity);
        if (result) {
            return new ResponseEntity<>("Entity created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create entity", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEntity(@PathVariable Long id, @RequestBody EntityName entity) {
        EntityName existingEntity = entityNameService.findById(id);
        if (existingEntity == null) {
            return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
        }

        entity.setId(id);
        Boolean result = entityNameService.update(entity);

        if (result) {
            return new ResponseEntity<>("Entity updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update entity", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEntity(@PathVariable Long id) {
        EntityName existingEntity = entityNameService.findById(id);
        if (existingEntity == null) {
            return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
        }

        Boolean result = entityNameService.delete(existingEntity);

        if (result) {
            return new ResponseEntity<>("Entity deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete entity", HttpStatus.BAD_REQUEST);
        }
    }
}
```

## List of Entities to Implement

The following entities need repositories, services, and controllers:

1. User ✓
2. Mascota ✓
3. Agendamiento
4. AgendamientoServido
5. Critica
6. EstadosPedido
7. Inventario
8. MediosPago
9. OrdenCompra
10. Pago
11. PersonalAccessToken
12. Producto
13. ProductoProveedor
14. Proveedore
15. Servicio
16. Venta

## Special Considerations

- For entities with relationships, make sure to create appropriate query methods in the repository interfaces.
- Adjust the service and controller methods as needed based on the specific requirements of each entity.
- Consider adding validation for request bodies in controllers.
- For entities with special business logic, add appropriate methods to the service interfaces and implementations.

## Example Implementation

User and Mascota entities have been fully implemented as examples. Follow the same pattern for the remaining entities.