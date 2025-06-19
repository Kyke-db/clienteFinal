package com.digipymes360.clienteFinal.controller;

import com.digipymes360.clienteFinal.model.Negocio;
import com.digipymes360.clienteFinal.service.NegocioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/productos")
public class NegocioController {
    @Autowired
    private NegocioService negocioService;

    // ——— CREAR ———
    @PostMapping("/Crear")
    public ResponseEntity<Negocio> crearNegocio(@RequestBody Negocio negocio) {
        Negocio creado = negocioService.crear(negocio);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // ——— LISTAR TODOS ———
    @GetMapping("/listar")
    public ResponseEntity<List<Negocio>> todos() {
        return ResponseEntity.ok(negocioService.listarTodos());
    }

    // ——— BUSCAR POR ID ———
    @GetMapping("/BuscarPorID/{id}")
    public ResponseEntity<Negocio> obtenerPorId(@PathVariable Integer id) {
        return negocioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ——— ACTUALIZAR ———
    @PutMapping("/Actualizar/{id}")
    public ResponseEntity<Negocio> actualizar(@PathVariable Integer id,
                                              @RequestBody Negocio datos) {
        try {
            Negocio actualizado = negocioService.actualizar(id, datos);
            return ResponseEntity.ok(actualizado);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ——— BORRAR ———
    @DeleteMapping("/Borrar{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (negocioService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

 
}
