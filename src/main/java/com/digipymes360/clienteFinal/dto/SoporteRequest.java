package com.digipymes360.clienteFinal.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SoporteRequest {
    private Long clienteId;
    private LocalDateTime fecha;
    private String estado;
    private String mensaje;
}
