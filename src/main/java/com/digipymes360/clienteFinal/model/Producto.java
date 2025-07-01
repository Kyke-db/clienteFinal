package com.digipymes360.clienteFinal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;



@Entity
@Table(name = "Producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autogenerado en Oracle
    @Column(name = "ID_PRODUCTO")
    @JsonProperty("id")
    private Long idProducto;

    @Column(name = "ID_NEGOCIO", nullable = false)
    private Long idNegocio;

    @Column(name = "PRECIO", nullable = false)
    private Float precio;

    @Column(name = "STOCK", nullable = false)
    private Long stock;

    @Column(name = "DESCRIPCION", length = 255)
    private String descripcion;

    @Column(name = "NOMBRE", length = 100, nullable = false)
    private String nombre;
}
