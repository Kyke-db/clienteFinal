package com.digipymes360.clienteFinal.controller;
import org.springframework.hateoas.EntityModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import com.digipymes360.clienteFinal.dto.PedidoRequest;
import com.digipymes360.clienteFinal.model.Pedido;
import com.digipymes360.clienteFinal.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Pedidos", description = "CRUD de pedidos")
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Operation(summary = "Crear Pedido")
    @PostMapping
    public Pedido crearPedido(@RequestBody PedidoRequest request) {
        return pedidoService.crearPedido(request);
    }

    @Operation(summary = "Obtener lista de todos los  pedidos")
    @GetMapping
    public List<Pedido> obtenerTodos() {
        return pedidoService.obtenerTodos();
    }

    @Operation(summary = "Obtener pedido por id")
    @GetMapping("/{id}")
    public EntityModel<Pedido> getPedidoById(@PathVariable Long id) {
        Pedido pedido = pedidoService.obtenerPorId(id);
        return EntityModel.of(pedido,
            linkTo(methodOn(PedidoController.class).getPedidoById(id)).withSelfRel(),
            linkTo(PedidoController.class).withRel("all"));
    }


    @Operation(summary = "eliminar pedido por id")
    @DeleteMapping("/{id}")
    public void eliminarPedido(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
    }
}
