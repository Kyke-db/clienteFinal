package com.digipymes360.clienteFinal.service;

import com.digipymes360.clienteFinal.dto.ResenaRequest;
import com.digipymes360.clienteFinal.model.Cliente;
import com.digipymes360.clienteFinal.model.Producto;
import com.digipymes360.clienteFinal.model.Resena;
import com.digipymes360.clienteFinal.repository.ClienteRepository;
import com.digipymes360.clienteFinal.repository.ProductoRepository;
import com.digipymes360.clienteFinal.repository.ResenaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResenaServiceTest {

    @Mock
    private ResenaRepository resenaRepository;

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ResenaService resenaService;

    private Producto producto;
    private Cliente cliente;

    @BeforeEach
    void init() {
        producto = new Producto();
        producto.setIdProducto(1L);

        cliente = new Cliente();
        cliente.setId(2);           // usa tu setter real; en tu entidad es “id”

    }

    @Test
    void dejarResena_ok_devuelveResenaGuardada() {
        // Arrange
        ResenaRequest request = new ResenaRequest();
        request.setIdProducto(1L);
        request.setIdCliente(2L);
        request.setCalificacion(5);
        request.setComentario("Excelente");

        Resena resenaGuardada = new Resena();
        resenaGuardada.setId_resena(11);
        resenaGuardada.setCalificacion(5);
        resenaGuardada.setComentario("Excelente");
        resenaGuardada.setFecha(LocalDateTime.now());

        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(clienteRepository.findById(2L)).thenReturn(Optional.of(cliente));
        when(resenaRepository.save(any(Resena.class))).thenReturn(resenaGuardada);

        // Act
        Resena resultado = resenaService.dejarResena(request);

        // Assert
        assertThat(resultado)
                .extracting(Resena::getCalificacion, Resena::getComentario)
                .containsExactly(5, "Excelente");
        verify(resenaRepository).save(any(Resena.class));
    }

    @Test
    void dejarResena_productoNoExiste_devuelveNull() {
        ResenaRequest request = new ResenaRequest();
        request.setIdProducto(99L);
        request.setIdCliente(2L);

        when(productoRepository.findById(99L)).thenReturn(Optional.empty());

        Resena resultado = resenaService.dejarResena(request);

        assertThat(resultado).isNull();
        verify(resenaRepository, never()).save(any());
    }

    @Test
    void dejarResena_clienteNoExiste_devuelveNull() {
        ResenaRequest request = new ResenaRequest();
        request.setIdProducto(1L);
        request.setIdCliente(99L);

        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(clienteRepository.findById(99L)).thenReturn(Optional.empty());

        Resena resultado = resenaService.dejarResena(request);

        assertThat(resultado).isNull();
        verify(resenaRepository, never()).save(any());
    }
}
