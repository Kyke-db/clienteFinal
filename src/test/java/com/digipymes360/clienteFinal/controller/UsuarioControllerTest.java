package com.digipymes360.clienteFinal.controller;

import com.digipymes360.clienteFinal.model.Usuario;
import com.digipymes360.clienteFinal.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void post_registrarUsuario_devuelve200YObjeto() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId_usuario(1);
        usuario.setNombre("Kyke");
        usuario.setEmail("kyke@example.com");
        usuario.setPassword("1234");

        Usuario guardado = new Usuario();
        guardado.setId_usuario(1);  // <--- Asignar id_usuario aquí
        guardado.setRol(2);
        guardado.setNombre("Kyke");
        guardado.setEmail("kyke@example.com");
        guardado.setActivo(true);

        Mockito.when(usuarioService.registrarUsuario(any(Usuario.class))).thenReturn(guardado);

        mockMvc.perform(post("/api/usuarios/registro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id_usuario", is(1)))
            .andExpect(jsonPath("$.nombre", is("Kyke")))
            .andExpect(jsonPath("$.rol", is(2)));
    }

    @Test
    void put_actualizarUsuario_devuelveUsuarioActualizado() throws Exception {
        Usuario actualizado = new Usuario();
        actualizado.setRol(1);
        actualizado.setNombre("Nuevo");
        actualizado.setEmail("nuevo@email.com");
        actualizado.setRol(3);

        Mockito.when(usuarioService.actualizarUsuario(any(Integer.class), any(Usuario.class)))
               .thenReturn(actualizado);

        mockMvc.perform(put("/api/usuarios/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualizado)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.nombre", is("Nuevo")))
               .andExpect(jsonPath("$.email", is("nuevo@email.com")));
    }

    @Test
    void get_obtenerUsuarioPorId_devuelveUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId_usuario(1);
        usuario.setRol(1);
        usuario.setNombre("Kyke");

        Mockito.when(usuarioService.obtenerUsuarioPorId(1)).thenReturn(Optional.of(usuario));

        String response = mockMvc.perform(get("/api/usuarios/1"))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();

        System.out.println("JSON respuesta GET usuario: " + response);
    }

}
