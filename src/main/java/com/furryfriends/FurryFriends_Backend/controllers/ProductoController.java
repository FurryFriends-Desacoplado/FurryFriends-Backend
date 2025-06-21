package com.furryfriends.FurryFriends_Backend.controllers;

import com.furryfriends.FurryFriends_Backend.entities.Producto;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            return new ResponseEntity<>("Producto created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create producto", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Producto existingProducto = productoService.findById(id);
        if (existingProducto == null) {
            return new ResponseEntity<>("Producto not found", HttpStatus.NOT_FOUND);
        }

        producto.setId(id);
        Boolean result = productoService.update(producto);

        if (result) {
            return new ResponseEntity<>("Producto updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update producto", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProducto(@PathVariable Long id) {
        Producto existingProducto = productoService.findById(id);
        if (existingProducto == null) {
            return new ResponseEntity<>("Producto not found", HttpStatus.NOT_FOUND);
        }

        Boolean result = productoService.delete(existingProducto);

        if (result) {
            return new ResponseEntity<>("Producto deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete producto", HttpStatus.BAD_REQUEST);
        }
    }
}
