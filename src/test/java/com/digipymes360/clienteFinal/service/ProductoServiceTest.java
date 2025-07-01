package com.digipymes360.clienteFinal.service;

import com.digipymes360.clienteFinal.model.Producto;
import com.digipymes360.clienteFinal.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerProductoPorId_Existe() {
        Producto p = new Producto();
        p.setIdProducto(1L);

        when(productoRepository.findById(1L)).thenReturn(Optional.of(p));

        Producto resultado = productoService.obtenerProductoPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdProducto());
        verify(productoRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerProductoPorId_NoExiste() {
        when(productoRepository.findById(2L)).thenReturn(Optional.empty());

        Producto resultado = productoService.obtenerProductoPorId(2L);

        assertNull(resultado);
        verify(productoRepository, times(1)).findById(2L);
    }

    @Test
    void testBuscarPorRangoDePrecio() {
        Producto p1 = new Producto();
        p1.setIdProducto(1L);
        Producto p2 = new Producto();
        p2.setIdProducto(2L);

        List<Producto> lista = Arrays.asList(p1, p2);

        when(productoRepository.findByPrecioBetween(10f, 100f)).thenReturn(lista);

        List<Producto> resultado = productoService.buscarPorRangoDePrecio(10f, 100f);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(productoRepository, times(1)).findByPrecioBetween(10f, 100f);
    }
}
