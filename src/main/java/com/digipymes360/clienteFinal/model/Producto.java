package com.digipymes360.clienteFinal.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_producto;

    @ManyToOne
    @JoinColumn(name = "id_negocio")
    private Negocio negocio;

    private String nombre;

    private String descripcion;

    private double precio; // <- tipo double

    private int stock;
}
