package com.furryfriends.FurryFriends_Backend.controllers;

import com.furryfriends.FurryFriends_Backend.entities.Producto;
import com.furryfriends.FurryFriends_Backend.enums.ProductStatus;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoService.findAll();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        Producto producto = productoService.findById(id);
        if (producto != null) {
            return new ResponseEntity<>(producto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> createProducto(@RequestBody Producto producto) {
        Boolean result = productoService.create(producto);
        if (result) {
            return new ResponseEntity<>("Producto creado exitosamente", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Error al crear el producto", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Producto existingProducto = productoService.findById(id);
        if (existingProducto == null) {
            return new ResponseEntity<>("Producto no encontrado", HttpStatus.NOT_FOUND);
        }

        producto.setId(id);
        Boolean result = productoService.update(producto);

        if (result) {
            return new ResponseEntity<>("Producto actualizado exitosamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error al actualizar el producto", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProducto(@PathVariable Long id) {
        Producto existingProducto = productoService.findById(id);
        if (existingProducto == null) {
            return new ResponseEntity<>("Producto no encontrado", HttpStatus.NOT_FOUND);
        }

        Boolean result = productoService.delete(existingProducto);

        if (result) {
            return new ResponseEntity<>("Producto eliminado exitosamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Error al eliminar el producto", HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}/product-status")
    public ResponseEntity<?> updateProductStatus(@PathVariable Long id, @RequestBody Map<String, String> request) {
        String statusStr = request.get("productStatus");

        try {
            ProductStatus newStatus = ProductStatus.valueOf(statusStr);
            boolean updated = productoService.updateProductStatus(id, newStatus);

            if (!updated) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
            }

            return ResponseEntity.ok("Estado del producto actualizado correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Estado inválido: " + statusStr);
        }
    }
}
