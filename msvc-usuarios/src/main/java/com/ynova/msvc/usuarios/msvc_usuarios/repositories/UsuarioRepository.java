package com.ynova.msvc.usuarios.msvc_usuarios.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ynova.msvc.usuarios.msvc_usuarios.model.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

}
