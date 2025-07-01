package com.digipymes360.clienteFinal.service;

import com.digipymes360.clienteFinal.model.Usuario;
import com.digipymes360.clienteFinal.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;

    @BeforeEach
    void init() {
        usuario = new Usuario();
        usuario.setRol(1);
        usuario.setNombre("Kyke");
        usuario.setEmail("kyke@example.com");
        usuario.setPassword("1234");
        usuario.setRol(1);
    }

    @Test
    void registrarUsuario_ok_registraYDevuelve() throws Exception {
        when(usuarioRepository.findByEmail("kyke@example.com")).thenReturn(Optional.empty());
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario resultado = usuarioService.registrarUsuario(usuario);

        assertThat(resultado.getNombre()).isEqualTo("Kyke");
        assertThat(resultado.isActivo()).isTrue();
        assertThat(resultado.getRol()).isEqualTo(2);
    }

    @Test
    void registrarUsuario_duplicado_lanzaExcepcion() {
        when(usuarioRepository.findByEmail("kyke@example.com")).thenReturn(Optional.of(usuario));

        assertThatThrownBy(() -> usuarioService.registrarUsuario(usuario))
                .isInstanceOf(Exception.class)
                .hasMessage("El correo ya está registrado.");
    }

    @Test
    void actualizarUsuario_existente_actualizaYDevuelve() {
        Usuario actualizacion = new Usuario();
        actualizacion.setNombre("Nuevo Nombre");
        actualizacion.setEmail("nuevo@email.com");
        actualizacion.setPassword("pass");
        actualizacion.setRol(3);

        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario resultado = usuarioService.actualizarUsuario(1, actualizacion);

        assertThat(resultado.getNombre()).isEqualTo("Nuevo Nombre");
        assertThat(resultado.getEmail()).isEqualTo("nuevo@email.com");
        assertThat(resultado.getRol()).isEqualTo(3);
    }

    @Test
    void obtenerUsuarioPorId_existente_devuelveUsuario() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = usuarioService.obtenerUsuarioPorId(1);

        assertThat(resultado).isPresent().contains(usuario);
    }
}
