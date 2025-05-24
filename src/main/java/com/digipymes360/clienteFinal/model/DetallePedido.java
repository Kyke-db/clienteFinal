package com.digipymes360.clienteFinal.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Detalle_Pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_detalle_pedido;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;


    private Integer cantidad;

    private double subtotal; // <- tipo double
}
