package com.digipymes360.clienteFinal.service;


import com.digipymes360.clienteFinal.model.Soporte;
import com.digipymes360.clienteFinal.repository.SoporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SoporteService {

    @Autowired
    private SoporteRepository soporteRepository;
    //enviar mensaje
    public Soporte enviarMensaje(Soporte soporte) {
        soporte.setFecha(new Date());
        soporte.setEstado("pendiente"); // Por defecto
        return soporteRepository.save(soporte);
    }
    //obtener los mensajes
    public List<Soporte> obtenerTodosLosMensajes() {
        return soporteRepository.findAll();
    }

    public Soporte cambiarEstado(Integer id, String nuevoEstado) {
        Soporte soporte = soporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el mensaje con id: " + id));
        soporte.setEstado(nuevoEstado);
        return soporteRepository.save(soporte);
    }
}
