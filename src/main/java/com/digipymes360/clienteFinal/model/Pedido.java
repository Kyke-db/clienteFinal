package com.digipymes360.clienteFinal.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pedido;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


    private Date fecha;

    private String estado;

    private String metodo_envio;

    private double total; // <- tipo double

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<DetallePedido> detalles;
}
