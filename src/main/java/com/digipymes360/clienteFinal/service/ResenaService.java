//listo completo

package com.digipymes360.clienteFinal.service;

import com.digipymes360.clienteFinal.dto.ResenaRequest;
import com.digipymes360.clienteFinal.model.Cliente;
import com.digipymes360.clienteFinal.model.Producto;
import com.digipymes360.clienteFinal.model.Resena;
import com.digipymes360.clienteFinal.repository.ClienteRepository;
import com.digipymes360.clienteFinal.repository.ProductoRepository;
import com.digipymes360.clienteFinal.repository.ResenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Resena dejarResena(ResenaRequest request) {
        Producto producto = productoRepository.findById(request.getIdProducto()).orElse(null);
        Cliente cliente = clienteRepository.findById(request.getIdCliente()).orElse(null);

        if (producto == null || cliente == null) {
            return null; // puedes manejarlo con errores personalizados si quieres después
        }

        Resena resena = new Resena();
        resena.setProducto(producto);
        resena.setCliente(cliente);
        resena.setCalificacion(request.getCalificacion());
        resena.setComentario(request.getComentario());
        resena.setFecha(LocalDateTime.now());

        return resenaRepository.save(resena);
    }
}