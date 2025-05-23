package com.digipymes360.clienteFinal.controller;

import com.digipymes360.clienteFinal.model.Soporte;
import com.digipymes360.clienteFinal.service.SoporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/soporte")
public class SoporteController {

    @Autowired
    private SoporteService soporteService;

    @PostMapping("/enviar")
    public ResponseEntity<?> enviarSoporte(@RequestBody Soporte soporte) {
        try {
            Soporte nuevo = soporteService.enviarMensaje(soporte);
            return ResponseEntity.ok(nuevo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/mensajes")
    public ResponseEntity<List<Soporte>> obtenerMensajes() {
        return ResponseEntity.ok(soporteService.obtenerTodosLosMensajes());
    }

    @PutMapping("/estado/{id}")
    public ResponseEntity<?> cambiarEstado(@PathVariable Integer id, @RequestParam String estado) {
        try {
            Soporte actualizado = soporteService.cambiarEstado(id, estado);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
