package com.digipymes360.clienteFinal.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Negocio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Negocio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_negocio;

    @ManyToOne
    @JoinColumn(name = "id_dueno", referencedColumnName = "id_usuario")
    private Usuario dueno;

    private String nombre;
    private String horario;
    private String direccion;

    @Column(columnDefinition = "TEXT")
    private String promociones;

    @Column(name = "estrategia_fidelizacion", columnDefinition = "TEXT")
    private String estrategiaFidelizacion;
}
