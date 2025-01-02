package com.ynova.msvc.cursos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynova.msvc.cursos.models.Usuario;
import com.ynova.msvc.cursos.models.entity.Curso;
import com.ynova.msvc.cursos.repositories.CursoRepository;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Curso> findAll() {
        return (List<Curso>) cursoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> findById(Long id) {
        return cursoRepository.findById(id);
    }

    @Override
    @Transactional
    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        cursoRepository.deleteById(id);
    }

    @Override
    public Optional<Usuario> assingUser(Usuario usuario, Long cursoId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assingUser'");
    }

    @Override
    public Optional<Usuario> addUder(Usuario usuario, Long cursoId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addUder'");
    }

    @Override
    public Optional<Usuario> removeUser(Usuario usuario, Long cursoId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeUser'");
    }

}
