package com.digipymes360.clienteFinal.controller;

import com.digipymes360.clienteFinal.model.Producto;
import com.digipymes360.clienteFinal.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping("/CrearProducto")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto p) {
        Producto creado = productoService.crear(p);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping("/BuscarPorID/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Integer id) {
        return productoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/Actualizar/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Integer id,
                                               @RequestBody Producto datos) {
        try {
            Producto actualizado = productoService.actualizar(id, datos);
            return ResponseEntity.ok(actualizado);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/buscar")
    public List<Producto> buscar(@RequestParam String nombre) {
        return productoService.buscarPorNombre(nombre);
    }

    @GetMapping("/filtrar/precio")
    public List<Producto> filtrarPrecio(@RequestParam Double min, @RequestParam Double max) {
        return productoService.filtrarPorPrecio(min, max);
    }

    @GetMapping("/disponibles")
    public List<Producto> disponibles(@RequestParam(defaultValue = "0") Integer stock) {
        return productoService.disponibles(stock);
    }

    @GetMapping("/negocio/{id}")
    public List<Producto> porNegocio(@PathVariable Integer id) {
        return productoService.porNegocio(id);
    }

    @GetMapping
    public List<Producto> todos() {
        return productoService.listarTodos();
    }
}
