package com.digipymes360.clienteFinal.repository;

import com.digipymes360.clienteFinal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
//Acceso a la base de datos
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
}
