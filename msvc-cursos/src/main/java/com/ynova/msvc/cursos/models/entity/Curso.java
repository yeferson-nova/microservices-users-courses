package com.ynova.msvc.cursos.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;


@Entity
@Table(name = "cursos")
@Data
@AllArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "curso_id")
    private List<CursoUsuario> cursoUsuarios;

    private String description;

    public Curso() {
        cursoUsuarios = new ArrayList<>();
    }

    public void addCursoUsuario(CursoUsuario cursousuario) {
        cursoUsuarios.add(cursousuario);
    }

    public void removeCursoUsuario(CursoUsuario cursousuario) {
        cursoUsuarios.remove(cursousuario);
    }
}
