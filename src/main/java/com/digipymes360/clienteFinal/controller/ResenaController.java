package com.digipymes360.clienteFinal.controller;

import com.digipymes360.clienteFinal.dto.ResenaRequest;
import com.digipymes360.clienteFinal.model.Resena;
import com.digipymes360.clienteFinal.service.ResenaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resenas")
@Tag(name = "Reseña", description = "Dejar Reseña")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @Operation(summary = "Dejar reseñas")
    @PostMapping
    public Resena dejarResena(@RequestBody ResenaRequest request) {
        return resenaService.dejarResena(request);
    }
}