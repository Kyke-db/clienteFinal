package com.digipymes360.clienteFinal.service;

import com.digipymes360.clienteFinal.model.Producto;
import com.digipymes360.clienteFinal.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Producto crear(Producto producto) {
        return productoRepository.save(producto);
    }


    public Optional<Producto> buscarPorId(Integer id) {
        return productoRepository.findById(id);
    }

    public Producto actualizar(Integer id, Producto datos) {
        Producto original = productoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado con id " + id));
        // Actualiza campo a campo
        original.setNombre(datos.getNombre());
        original.setDescripcion(datos.getDescripcion());
        original.setPrecio(datos.getPrecio());
        original.setStock(datos.getStock());
        // ... otros campos si los hubiera
        return productoRepository.save(original);
    }

    public boolean eliminar(Integer id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Producto> filtrarPorPrecio(Double min, Double max) {
        return productoRepository.findByPrecioBetween(min, max);
    }

    public List<Producto> disponibles(Integer minimoStock) {
        return productoRepository.findByStockGreaterThan(minimoStock);
    }

    public List<Producto> porNegocio(Integer idNegocio) {
        return productoRepository.findByNegocioIdNegocio(idNegocio);
    }

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }
}
