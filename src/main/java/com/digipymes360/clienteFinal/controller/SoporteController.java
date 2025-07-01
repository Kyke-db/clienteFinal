package com.digipymes360.clienteFinal.controller;


import com.digipymes360.clienteFinal.dto.SoporteRequest;
import com.digipymes360.clienteFinal.model.Soporte;
import com.digipymes360.clienteFinal.service.SoporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/soporte")
public class SoporteController {

    @Autowired
    private SoporteService soporteService;

    @PostMapping
    public Soporte crearSoporte(@RequestBody SoporteRequest request) {
        return soporteService.crearSoporte(request);
    }

    @GetMapping
    public List<Soporte> obtenerTodos() {
        return soporteService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Soporte obtenerPorId(@PathVariable Long id) {
        return soporteService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarSoporte(@PathVariable Long id) {
        soporteService.eliminarSoporte(id);
    }
}
