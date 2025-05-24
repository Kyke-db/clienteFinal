package com.digipymes360.clienteFinal.service;

import com.digipymes360.clienteFinal.model.DetallePedido;
import com.digipymes360.clienteFinal.model.Pedido;
import com.digipymes360.clienteFinal.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


import java.sql.Date;


@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido crearPedido(Pedido pedido) {
        pedido.setFecha(new Date(0));
        pedido.setEstado("Pendiente");

        double total = 0;
        for (DetallePedido detalle : pedido.getDetalles()) {
            double subtotal = detalle.getProducto().getPrecio() * detalle.getCantidad();
            detalle.setSubtotal(subtotal);
            detalle.setPedido(pedido);
            total += subtotal;
        }

        pedido.setTotal(total);
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> obtenerPedidosPorCliente(Integer idCliente) {
        return pedidoRepository.findByClienteId(idCliente);
    }
}
