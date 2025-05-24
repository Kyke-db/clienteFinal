package com.digipymes360.clienteFinal.service;

import com.digipymes360.clienteFinal.model.Resena;
import com.digipymes360.clienteFinal.repository.ResenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    public Resena dejarResena(Resena resena) {
        resena.setFecha(LocalDateTime.now());
        return resenaRepository.save(resena);
    }

    public List<Resena> obtenerResenasPorProducto(Integer idProducto) {
        return resenaRepository.findAll().stream()
                .filter(r -> r.getProducto().getId_producto().equals(idProducto))
                .toList();
    }
}
