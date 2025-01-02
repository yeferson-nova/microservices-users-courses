package com.ynova.msvc.cursos.services;

import java.util.List;
import java.util.Optional;

import com.ynova.msvc.cursos.models.Usuario;
import com.ynova.msvc.cursos.models.entity.Curso;

public interface CursoService {
    List<Curso> findAll();

    Optional<Curso> findById(Long id);

    Curso save(Curso curso);

    void deleteById(Long id);

    Optional<Usuario> assingUser(Usuario usuario, Long cursoId);

    Optional<Usuario> addUder(Usuario usuario, Long cursoId);

    Optional<Usuario> removeUser(Usuario usuario, Long cursoId);

}
