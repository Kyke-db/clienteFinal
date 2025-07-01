package com.digipymes360.clienteFinal.controller;

import com.digipymes360.clienteFinal.dto.ResenaRequest;
import com.digipymes360.clienteFinal.model.Resena;
import com.digipymes360.clienteFinal.service.ResenaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ResenaController.class)
class ResenaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResenaService resenaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void post_dejarResena_devuelve200YObjeto() throws Exception {
        // Entrada
        ResenaRequest request = new ResenaRequest();
        request.setIdProducto(1L);
        request.setIdCliente(2L);
        request.setCalificacion(4);
        request.setComentario("Muy bueno");

        // Salida mock
        Resena resenaRespuesta = new Resena();
        resenaRespuesta.setId_resena(15);
        resenaRespuesta.setCalificacion(4);
        resenaRespuesta.setComentario("Muy bueno");
        resenaRespuesta.setFecha(LocalDateTime.now());

        Mockito.when(resenaService.dejarResena(any(ResenaRequest.class))).thenReturn(resenaRespuesta);

        mockMvc.perform(post("/api/resenas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id_resena", is(15)))
               .andExpect(jsonPath("$.calificacion", is(4)))
               .andExpect(jsonPath("$.comentario", is("Muy bueno")));
    }
}
