package com.ynova.msvc.cursos.services;

import java.util.List;
import java.util.Optional;

import com.ynova.msvc.cursos.entity.Curso;

public interface CursoService {
    List<Curso> findAll();

    Optional<Curso> findById(Long id);

    Curso save(Curso curso);

    void deleteById(Long id);

}
