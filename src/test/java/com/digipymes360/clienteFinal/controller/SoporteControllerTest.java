package com.digipymes360.clienteFinal.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import com.digipymes360.clienteFinal.model.Soporte;
import com.digipymes360.clienteFinal.service.SoporteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SoporteController.class)
public class SoporteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SoporteService soporteService;

    @Test
    void testGetSoporteById_Exists() throws Exception {
        Soporte s = new Soporte();
        s.setId_soporte(1L);

        when(soporteService.obtenerPorId(1L)).thenReturn(s);

        mockMvc.perform(get("/api/v1/soporte"))
               .andExpect(status().isOk())
               .andDo(print());
    }

    @Test
    void testGetSoporteById_NotFound() throws Exception {
        when(soporteService.obtenerPorId(2L)).thenReturn(null);

        mockMvc.perform(get("/api/v1/soporte/2"))
               .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllSoporte_Empty() throws Exception {
        when(soporteService.obtenerTodos())
            .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/soporte"))
               .andExpect(status().isOk())
               .andExpect(content().json("[]"));
    }
}
