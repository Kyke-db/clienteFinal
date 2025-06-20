package com.digipymes360.clienteFinal.service;



import com.digipymes360.clienteFinal.dto.SoporteRequest;
import com.digipymes360.clienteFinal.model.Cliente;
import com.digipymes360.clienteFinal.model.Soporte;
import com.digipymes360.clienteFinal.repository.ClienteRepository;
import com.digipymes360.clienteFinal.repository.SoporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoporteService {

    @Autowired
    private SoporteRepository soporteRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Soporte crearSoporte(SoporteRequest request) {
        Cliente cliente = clienteRepository.findById(request.getClienteId()).orElse(null);

        Soporte soporte = new Soporte();
        soporte.setCliente(cliente);
        soporte.setFecha(request.getFecha());
        soporte.setEstado(request.getEstado());
        soporte.setMensaje(request.getMensaje());

        return soporteRepository.save(soporte);
    }

    public List<Soporte> obtenerTodos() {
        return soporteRepository.findAll();
    }

    public Soporte obtenerPorId(Long id) {
        return soporteRepository.findById(id).orElse(null);
    }

    public void eliminarSoporte(Long id) {
        soporteRepository.deleteById(id);
    }
}
