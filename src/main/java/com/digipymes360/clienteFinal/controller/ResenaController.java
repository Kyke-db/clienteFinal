package com.digipymes360.clienteFinal.controller;

import com.digipymes360.clienteFinal.dto.ResenaRequest;
import com.digipymes360.clienteFinal.model.Resena;
import com.digipymes360.clienteFinal.service.ResenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resenas")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @PostMapping
    public Resena dejarResena(@RequestBody ResenaRequest request) {
        return resenaService.dejarResena(request);
    }
}