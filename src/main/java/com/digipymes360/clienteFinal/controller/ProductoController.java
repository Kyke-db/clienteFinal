package com.digipymes360.clienteFinal.controller;

import com.digipymes360.clienteFinal.model.Producto;
import com.digipymes360.clienteFinal.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
@Tag(name = "Producto", description = "Endpoints para productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @Operation(summary = "Buscar producto por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoPorId(@PathVariable Long id) {
        Producto producto = productoService.obtenerProductoPorId(id);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.ok(null); // o ResponseEntity.notFound().build(); si después quieres agregarlo
        }
    }

    @Operation(summary = "Buscar productos por rango de precios")
    @GetMapping("/precio")
    public ResponseEntity<List<Producto>> getProductosPorPrecio(
            @RequestParam Float minimo,
            @RequestParam Float maximo) {
        List<Producto> productos = productoService.buscarPorRangoDePrecio(minimo, maximo);
        return ResponseEntity.ok(productos);
    }
}