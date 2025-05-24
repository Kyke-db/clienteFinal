package com.digipymes360.clienteFinal.repository;

import com.digipymes360.clienteFinal.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByClienteId(Integer id);
}