package com.digipymes360.clienteFinal.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

import com.digipymes360.clienteFinal.model.Usuario;
import com.digipymes360.clienteFinal.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuario", description = "Registo y actualizacion de usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    
    @Operation(summary = "registrar usuario en la base de datos")
    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioService.registrarUsuario(usuario);
            return ResponseEntity.ok(nuevoUsuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "actualizar perfil de usuario")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPerfil(
            @PathVariable Integer id,
            @RequestBody Usuario usuarioActualizado) {
        try {
            Usuario actualizado = usuarioService.actualizarUsuario(id, usuarioActualizado);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "buscar usuario por id")
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable Integer id) {
        Optional<Usuario> usuario = usuarioService.obtenerUsuarioPorId(id);
        return usuario.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }


    
}

