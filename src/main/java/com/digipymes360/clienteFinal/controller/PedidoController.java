package com.digipymes360.clienteFinal.controller;

import com.digipymes360.clienteFinal.model.Pedido;
import com.digipymes360.clienteFinal.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/realizar")
    public ResponseEntity<Pedido> realizarPedido(@RequestBody Pedido pedido) {
        try {
            Pedido nuevoPedido = pedidoService.crearPedido(pedido);
            return ResponseEntity.ok(nuevoPedido);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/historial/{idCliente}")
    public List<Pedido> obtenerHistorialPedidos(@PathVariable Integer idCliente) {
        return pedidoService.obtenerPedidosPorCliente(idCliente);
    }
}
