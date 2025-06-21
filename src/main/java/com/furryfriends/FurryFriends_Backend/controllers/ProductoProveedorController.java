package com.furryfriends.FurryFriends_Backend.controllers;

import com.furryfriends.FurryFriends_Backend.entities.ProductoProveedor;
import com.furryfriends.FurryFriends_Backend.services.interfaces.IProductoProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producto-proveedores")
@CrossOrigin(origins = "*")
public class ProductoProveedorController {

    @Autowired
    private IProductoProveedorService productoProveedorService;

    @GetMapping
    public ResponseEntity<List<ProductoProveedor>> getAllProductoProveedores() {
        List<ProductoProveedor> productoProveedores = productoProveedorService.findAll();
        return new ResponseEntity<>(productoProveedores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoProveedor> getProductoProveedorById(@PathVariable Long id) {
        ProductoProveedor productoProveedor = productoProveedorService.findById(id);
        if (productoProveedor != null) {
            return new ResponseEntity<>(productoProveedor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> createProductoProveedor(@RequestBody ProductoProveedor productoProveedor) {
        Boolean result = productoProveedorService.create(productoProveedor);
        if (result) {
            return new ResponseEntity<>("ProductoProveedor created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create ProductoProveedor", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProductoProveedor(@PathVariable Long id, @RequestBody ProductoProveedor productoProveedor) {
        ProductoProveedor existingProductoProveedor = productoProveedorService.findById(id);
        if (existingProductoProveedor == null) {
            return new ResponseEntity<>("ProductoProveedor not found", HttpStatus.NOT_FOUND);
        }

        productoProveedor.setId(id);
        Boolean result = productoProveedorService.update(productoProveedor);

        if (result) {
            return new ResponseEntity<>("ProductoProveedor updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update ProductoProveedor", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductoProveedor(@PathVariable Long id) {
        ProductoProveedor existingProductoProveedor = productoProveedorService.findById(id);
        if (existingProductoProveedor == null) {
            return new ResponseEntity<>("ProductoProveedor not found", HttpStatus.NOT_FOUND);
        }

        Boolean result = productoProveedorService.delete(existingProductoProveedor);

        if (result) {
            return new ResponseEntity<>("ProductoProveedor deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to delete ProductoProveedor", HttpStatus.BAD_REQUEST);
        }
    }
}
