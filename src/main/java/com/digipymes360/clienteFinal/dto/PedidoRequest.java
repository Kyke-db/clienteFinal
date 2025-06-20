package com.digipymes360.clienteFinal.dto;



import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PedidoRequest {

    private Long clienteId;
    private float total;
    private LocalDateTime fecha;
    private String estado;
    private String metodoEnvio;
}
