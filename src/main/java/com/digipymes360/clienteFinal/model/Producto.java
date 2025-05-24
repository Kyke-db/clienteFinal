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

    private String nombre;

    private String descripcion;

    private double precio; // <- tipo double

    private int stock;
}
