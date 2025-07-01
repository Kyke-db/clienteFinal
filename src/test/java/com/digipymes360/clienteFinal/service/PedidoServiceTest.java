package com.digipymes360.clienteFinal.service;

import com.digipymes360.clienteFinal.dto.PedidoRequest;
import com.digipymes360.clienteFinal.model.Cliente;
import com.digipymes360.clienteFinal.model.Pedido;
import com.digipymes360.clienteFinal.repository.ClienteRepository;
import com.digipymes360.clienteFinal.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private PedidoService pedidoService;

    private Cliente cliente;
    private Pedido pedidoMock;

    @BeforeEach
    void init() {
        cliente = new Cliente();
        cliente.setId(1);                       // el campo real de tu entidad es "id" (Integer)

        pedidoMock = new Pedido();
        pedidoMock.setId_pedido(10L);
        pedidoMock.setCliente(cliente);
        pedidoMock.setTotal(100.0f);
        pedidoMock.setFecha(LocalDateTime.now());
        pedidoMock.setEstado("Pendiente");
        pedidoMock.setMetodo_envio("Retiro");
    }

    @Test
    void crearPedido_guardaYDevuelvePedido() {
        // Arrange
        PedidoRequest request = new PedidoRequest();
        request.setClienteId(1L);               // Long
        request.setTotal(100.0f);               // float
        request.setFecha(pedidoMock.getFecha());
        request.setEstado("Pendiente");
        request.setMetodoEnvio("Retiro");

        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedidoMock);

        // Act
        Pedido resultado = pedidoService.crearPedido(request);

        // Assert
        assertThat(resultado)
                .extracting(Pedido::getTotal, Pedido::getEstado, p -> p.getCliente().getId())
                .containsExactly(100.0f, "Pendiente", 1);
        verify(pedidoRepository).save(any(Pedido.class));
    }

    @Test
    void obtenerTodos_devuelveLista() {
        when(pedidoRepository.findAll()).thenReturn(Arrays.asList(pedidoMock));

        List<Pedido> lista = pedidoService.obtenerTodos();

        assertThat(lista).hasSize(1).contains(pedidoMock);
        verify(pedidoRepository).findAll();
    }

    @Test
    void obtenerPorId_existente_devuelvePedido() {
        when(pedidoRepository.findById(10L)).thenReturn(Optional.of(pedidoMock));

        Pedido encontrado = pedidoService.obtenerPorId(10L);

        assertThat(encontrado).isEqualTo(pedidoMock);
        verify(pedidoRepository).findById(10L);
    }

    @Test
    void eliminarPedido_invocaDeleteById() {
        doNothing().when(pedidoRepository).deleteById(10L);

        pedidoService.eliminarPedido(10L);

        verify(pedidoRepository).deleteById(10L);
    }
}