package com.digipymes360.clienteFinal.controller;

import com.digipymes360.clienteFinal.model.Producto;
import com.digipymes360.clienteFinal.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

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
