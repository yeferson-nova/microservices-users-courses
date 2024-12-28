package com.ynova.msvc.usuarios.services;

import java.util.List;
import java.util.Optional;

import com.ynova.msvc.usuarios.model.entity.Usuario;

public interface UsuarioService {

    List<Usuario> findAll();
    Optional<Usuario> findById(Long id);
    Usuario save(Usuario usuario);
    void deleteById(Long id);

}
