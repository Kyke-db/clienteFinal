package com.digipymes360.clienteFinal.controller;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import com.digipymes360.clienteFinal.model.Producto;
import com.digipymes360.clienteFinal.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/producto")
@Tag(name = "Producto", description = "Busqueda de producto")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @Operation(summary = "Buscar producto por ID")
    @GetMapping("/{id}")
    public EntityModel<Producto> getProductoById(@PathVariable Long id) {
        Producto producto = productoService.obtenerProductoPorId(id);
        return EntityModel.of(producto,
            linkTo(methodOn(ProductoController.class).getProductoById(id)).withSelfRel(),
            linkTo(ProductoController.class).withRel("all"));
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