package com.digipymes360.clienteFinal.repository;

import com.digipymes360.clienteFinal.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    List<Producto> findByPrecioBetween(Double min, Double max);
    List<Producto> findByStockGreaterThan(Integer minStock);
    List<Producto> findByNegocioIdNegocio(Integer idNegocio);

}
