package com.digipymes360.clienteFinal.service;

import com.digipymes360.clienteFinal.model.Soporte;
import com.digipymes360.clienteFinal.repository.SoporteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SoporteServiceTest {

    @Mock
    private SoporteRepository soporteRepository;

    @InjectMocks
    private SoporteService soporteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerPorId_Existe() {
        Soporte s = new Soporte();
        s.setId_soporte(1L);

        when(soporteRepository.findById(1L))
            .thenReturn(Optional.of(s));

        Soporte resultado = soporteService.obtenerPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId_soporte());
        verify(soporteRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerPorId_NoExiste() {
        when(soporteRepository.findById(2L))
            .thenReturn(Optional.empty());

        Soporte resultado = soporteService.obtenerPorId(2L);

        assertNull(resultado);
        verify(soporteRepository, times(1)).findById(2L);
    }
}
