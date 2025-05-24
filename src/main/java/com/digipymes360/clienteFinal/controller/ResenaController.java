package com.digipymes360.clienteFinal.controller;

import com.digipymes360.clienteFinal.model.Resena;
import com.digipymes360.clienteFinal.service.ResenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resenas")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @PostMapping
    public Resena dejarResena(@RequestBody Resena resena) {
        return resenaService.dejarResena(resena);
    }

    @GetMapping("/producto/{idProducto}")
    public List<Resena> obtenerResenasPorProducto(@PathVariable Integer idProducto) {
        return resenaService.obtenerResenasPorProducto(idProducto);
    }
}
