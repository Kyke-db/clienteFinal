package com.digipymes360.clienteFinal.controller;

import com.digipymes360.clienteFinal.dto.PedidoRequest;
import com.digipymes360.clienteFinal.model.Pedido;
import com.digipymes360.clienteFinal.service.PedidoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PedidoController.class)
class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void post_crearPedido_devuelve200YObjeto() throws Exception {
        // Datos de entrada
        PedidoRequest request = new PedidoRequest();
        request.setClienteId(1L);
        request.setTotal(50.0f);
        request.setFecha(LocalDateTime.now());
        request.setEstado("Pendiente");
        request.setMetodoEnvio("Retiro");

        // Respuesta mock
        Pedido pedidoRespuesta = new Pedido();
        pedidoRespuesta.setId_pedido(5L);
        pedidoRespuesta.setTotal(50.0f);
        pedidoRespuesta.setEstado("Pendiente");

        Mockito.when(pedidoService.crearPedido(any(PedidoRequest.class))).thenReturn(pedidoRespuesta);

        mockMvc.perform(post("/api/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id_pedido", is(5)))
               .andExpect(jsonPath("$.total", is(50.0)))
               .andExpect(jsonPath("$.estado", is("Pendiente")));
    }

    @Test
    void get_obtenerTodos_devuelveLista() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setId_pedido(3L);

        Mockito.when(pedidoService.obtenerTodos()).thenReturn(Collections.singletonList(pedido));

        mockMvc.perform(get("/api/pedidos"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(1)))
               .andExpect(jsonPath("$[0].id_pedido", is(3)));
    }

    @Test
    void get_obtenerPorId_devuelvePedido() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setId_pedido(7L);

        Mockito.when(pedidoService.obtenerPorId(7L)).thenReturn(pedido);

        mockMvc.perform(get("/api/pedidos/7"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id_pedido", is(7)));
    }

    @Test
    void delete_eliminarPedido_devuelve200() throws Exception {
        Mockito.doNothing().when(pedidoService).eliminarPedido(9L);

        mockMvc.perform(delete("/api/pedidos/9"))
               .andExpect(status().isOk());

        Mockito.verify(pedidoService).eliminarPedido(9L);
    }
}