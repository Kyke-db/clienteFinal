package com.digipymes360.clienteFinal.repository;

import com.digipymes360.clienteFinal.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByPrecioBetween(Float minimo, Float maximo);
}