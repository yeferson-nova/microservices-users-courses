package com.ynova.msvc.usuarios.msvc_usuarios.services;

import java.util.List;
import java.util.Optional;

import com.ynova.msvc.usuarios.msvc_usuarios.model.entity.Usuario;

public interface UsuarioService {

    List<Usuario> findAll();
    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByEmail(String email);
    Usuario save(Usuario usuario);
    void deleteById(Long id);

}
