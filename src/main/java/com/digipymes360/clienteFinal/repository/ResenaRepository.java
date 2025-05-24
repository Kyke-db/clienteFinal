package com.digipymes360.clienteFinal.repository;

import com.digipymes360.clienteFinal.model.Resena;
import com.digipymes360.clienteFinal.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResenaRepository extends JpaRepository<Resena, Integer> {
    List<Resena> findByProducto(Producto producto);
}
