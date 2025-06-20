package com.digipymes360.clienteFinal.service;


import com.digipymes360.clienteFinal.model.Producto;
import com.digipymes360.clienteFinal.repository.ProductoRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class ProductoService {
     @Autowired
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Buscar producto por ID (puede devolver null si no existe)
    public Producto obtenerProductoPorId(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.orElse(null);
    }

    // Buscar productos entre precio mínimo y máximo
    public List<Producto> buscarPorRangoDePrecio(Float minimo, Float maximo) {
        return productoRepository.findByPrecioBetween(minimo, maximo);
    }
}