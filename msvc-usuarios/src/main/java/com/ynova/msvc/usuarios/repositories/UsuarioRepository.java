package com.ynova.msvc.usuarios.repositories;

import com.ynova.msvc.usuarios.model.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
   

}
