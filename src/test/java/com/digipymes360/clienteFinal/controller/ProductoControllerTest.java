package com.digipymes360.clienteFinal.controller;

import com.digipymes360.clienteFinal.model.Producto;
import com.digipymes360.clienteFinal.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @Test
    void testGetProductoById_Exists() throws Exception {
        Producto p = new Producto();
        p.setIdProducto(1L);

        when(productoService.obtenerProductoPorId(1L)).thenReturn(p);

        mockMvc.perform(get("/api/v1/producto/1"))
               .andExpect(status().isOk())
               .andDo(print())
               .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testGetProductoById_NotFound() throws Exception {
        when(productoService.obtenerProductoPorId(2L)).thenReturn(null);

        mockMvc.perform(get("/api/v1/producto/2"))
               .andExpect(status().isOk())  
               .andExpect(content().string(""));
    }

    @Test
    void testGetProductosPorPrecio() throws Exception {
        Producto p = new Producto();
        p.setIdProducto(3L);

        when(productoService.buscarPorRangoDePrecio(10f, 100f))
            .thenReturn(List.of(p));

        mockMvc.perform(get("/api/v1/producto/precio?minimo=10&maximo=100"))
               .andExpect(status().isOk())
               .andDo(print());
    }
}
