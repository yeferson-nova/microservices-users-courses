package com.ynova.msvc.cursos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ynova.msvc.cursos.entity.Curso;
import com.ynova.msvc.cursos.services.CursoService;

@RestController
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping({ "/cursos", "/" })
    public List<Curso> listar() {
        return cursoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        Optional<Curso> cursoOptional = cursoService.findById(id);
        if (cursoOptional.isPresent()) {
            return ResponseEntity.ok().body(cursoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping({ "/save", "/" })
    public ResponseEntity<?> guardar(@RequestBody Curso curso) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.save(curso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Curso curso, @PathVariable Long id) {
        Optional<Curso> cursoOptional = cursoService.findById(id);
        if (cursoOptional.isPresent()) {
            Curso cursoDB = cursoOptional.get();
            cursoDB.setName(curso.getName());
            cursoDB.setDescription(curso.getDescription());
            return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.save(cursoDB));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping({"/{id}","delete{id}"})
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        cursoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
