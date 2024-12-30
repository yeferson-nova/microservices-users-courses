package com.ynova.msvc.usuarios.msvc_usuarios.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ynova.msvc.usuarios.msvc_usuarios.model.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
   

}
