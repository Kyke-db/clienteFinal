package com.digipymes360.clienteFinal.service;

import com.digipymes360.clienteFinal.model.Negocio;
import com.digipymes360.clienteFinal.repository.NegocioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class NegocioService {

     @Autowired
    private NegocioRepository negocioRepository;

    // Crea un negocio (sin pedir id en el body, se genera solo)
    public Negocio crear(Negocio negocio) {
        return negocioRepository.save(negocio);
    }

    public List<Negocio> listarTodos() {
        return negocioRepository.findAll();
    }

    public Optional<Negocio> buscarPorId(Integer id) {
        return negocioRepository.findById(id);
    }

    public Negocio actualizar(Integer id, Negocio datos) {
        Negocio original = negocioRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Negocio no encontrado con id " + id));

        // Sobre-escribe los campos modificables (el id no se toca)
        
        original.setNombre(datos.getNombre());
        original.setHorario(datos.getHorario());
        original.setDireccion(datos.getDireccion());
        original.setPromociones(datos.getPromociones());
        original.setEstrategiaFidelizacion(datos.getEstrategiaFidelizacion());

        return negocioRepository.save(original);
    }

    public boolean eliminar(Integer id) {
        if (negocioRepository.existsById(id)) {
            negocioRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
    

