//listo completo
package com.digipymes360.clienteFinal.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digipymes360.clienteFinal.model.Usuario;
import com.digipymes360.clienteFinal.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //metodo para ingresar usuario en la base de datos

    public Usuario registrarUsuario(Usuario usuario) throws Exception {
        Optional<Usuario> existente = usuarioRepository.findByEmail(usuario.getEmail());
        if (existente.isPresent()) {
            throw new Exception("El correo ya está registrado.");
        }

        usuario.setActivo(true); // Por defecto el usuario se registra como activo
        usuario.setRol(2); // Por ejemplo, 2 = rol cliente. Esto lo puedes ajustar.
        return usuarioRepository.save(usuario);
    }

    //metodo para editar perfil de ususario

    public Usuario actualizarUsuario(Integer id, Usuario datosActualizados) {
    Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

        if (optionalUsuario.isPresent()) {
            Usuario usuarioExistente = optionalUsuario.get();
            usuarioExistente.setNombre(datosActualizados.getNombre());
            usuarioExistente.setEmail(datosActualizados.getEmail());
            usuarioExistente.setRol(datosActualizados.getRol());
            // Si deseas también permitir cambiar la contraseña:
            usuarioExistente.setPassword(datosActualizados.getPassword());

            return usuarioRepository.save(usuarioExistente);
        } else {
            throw new RuntimeException("Usuario no encontrado con id: " + id);
        }


    
    }
    //Agrega endpoint para obtener perfil
    public Optional<Usuario> obtenerUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id);
    }


}
