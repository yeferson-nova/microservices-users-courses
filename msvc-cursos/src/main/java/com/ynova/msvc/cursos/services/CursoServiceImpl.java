package com.ynova.msvc.cursos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynova.msvc.cursos.clients.UsuarioClientRest;
import com.ynova.msvc.cursos.models.Usuario;
import com.ynova.msvc.cursos.models.entity.Curso;
import com.ynova.msvc.cursos.models.entity.CursoUsuario;
import com.ynova.msvc.cursos.repositories.CursoRepository;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioClientRest usuarioClientRest;

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
    @Transactional
    @Transactional
    public Optional<Usuario> assingUser(Usuario usuario, Long cursoId) {
        Optional<Curso> cursoOptional = cursoRepository.findById(cursoId);
        if (cursoOptional.isPresent()) {
            Usuario usuarioMsvc = usuarioClientRest.buscar(usuario.getId());

            Curso curso = cursoOptional.get();
            CursoUsuario cursoUsuario = new CursoUsuario();

            cursoUsuario.setUsuarioId(usuarioMsvc.getId());
            curso.addCursoUsuario(cursoUsuario);
            cursoRepository.save(curso);
            return Optional.of(usuarioMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> addUder(Usuario usuario, Long cursoId) {

        Optional<Curso> cursoOptional = cursoRepository.findById(cursoId);
        if (cursoOptional.isPresent()) {
            Usuario usuarioMsvc = usuarioClientRest.guardar(usuario);

            Curso curso = cursoOptional.get();
            CursoUsuario cursoUsuario = new CursoUsuario();

            cursoUsuario.setUsuarioId(usuarioMsvc.getId());
            curso.addCursoUsuario(cursoUsuario);
            cursoRepository.save(curso);
            return Optional.of(usuarioMsvc);
        }
        return Optional.empty();

    }

    @Override
    @Transactional
    public Optional<Usuario> removeUser(Usuario usuario, Long cursoId) {
        Optional<Curso> cursoOptional = cursoRepository.findById(cursoId);
        if (cursoOptional.isPresent()) {
            Usuario usuarioMsvc = usuarioClientRest.buscar(usuario.getId());

            Curso curso = cursoOptional.get();
            CursoUsuario cursoUsuario = new CursoUsuario();

            cursoUsuario.setUsuarioId(usuarioMsvc.getId());
            curso.removeCursoUsuario(cursoUsuario);
            cursoRepository.save(curso);
            return Optional.of(usuarioMsvc);
        }
        return Optional.empty();
    }

}
