package com.digipymes360.clienteFinal.service;


import com.digipymes360.clienteFinal.dto.PedidoRequest;
import com.digipymes360.clienteFinal.model.Cliente;
import com.digipymes360.clienteFinal.model.Pedido;
import com.digipymes360.clienteFinal.repository.ClienteRepository;
import com.digipymes360.clienteFinal.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Pedido crearPedido(PedidoRequest request) {
        Cliente cliente = clienteRepository.findById(request.getClienteId()).orElse(null);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setTotal(request.getTotal());
        pedido.setFecha(request.getFecha());
        pedido.setEstado(request.getEstado());
        pedido.setMetodo_envio(request.getMetodoEnvio());

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> obtenerTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido obtenerPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public void eliminarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}
