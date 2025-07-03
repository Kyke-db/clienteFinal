package com.digipymes360.clienteFinal.controller;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.digipymes360.clienteFinal.dto.SoporteRequest;
import com.digipymes360.clienteFinal.model.Soporte;
import com.digipymes360.clienteFinal.service.SoporteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/soporte")
@Tag(name = "Usuario", description = "CRUD de soporte")
public class SoporteController {

    @Autowired
    private SoporteService soporteService;

    @Operation(summary = "abrir un tiket")
    @PostMapping
    public Soporte crearSoporte(@RequestBody SoporteRequest request) {
        return soporteService.crearSoporte(request);
    }

    @Operation(summary = "obtener todos los tikets")
    @GetMapping
    public List<Soporte> obtenerTodos() {
        return soporteService.obtenerTodos();
    }

    @Operation(summary = "buscar tiket por id") 
    @GetMapping("/{id}")
    public EntityModel<Soporte> getSoporteById(@PathVariable Long id) {
        Soporte soporte = soporteService.obtenerPorId(id);
        return EntityModel.of(soporte,
            linkTo(methodOn(SoporteController.class).getSoporteById(id)).withSelfRel(),
            linkTo(SoporteController.class).withRel("all"));
    }

    @Operation(summary = "eliminar tiket")
    @DeleteMapping("/{id}")
    public void eliminarSoporte(@PathVariable Long id) {
        soporteService.eliminarSoporte(id);
    }
}
