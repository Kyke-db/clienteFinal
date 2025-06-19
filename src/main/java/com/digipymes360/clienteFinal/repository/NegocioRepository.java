package com.digipymes360.clienteFinal.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digipymes360.clienteFinal.model.Negocio;


@Repository
public interface NegocioRepository extends JpaRepository<Negocio, Integer> {
    
}