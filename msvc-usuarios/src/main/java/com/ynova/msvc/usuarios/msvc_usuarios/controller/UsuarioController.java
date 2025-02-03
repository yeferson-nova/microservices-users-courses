package com.ynova.msvc.usuarios.msvc_usuarios.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ynova.msvc.usuarios.msvc_usuarios.model.entity.Usuario;
import com.ynova.msvc.usuarios.msvc_usuarios.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping({ "/usuarios", "/" })
    public List<Usuario> listar() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);
        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok().body(usuarioOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> guardar(@Valid @RequestBody Usuario usuario, BindingResult result) {

        if (result.hasErrors()) {
            return validar(result);
        }

        if (!usuario.getEmail().isEmpty() && usuarioService.existsByEmail(usuario.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", "El email ya existe"));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Usuario usuario, @PathVariable Long id, BindingResult result) {

        if (result.hasErrors()) {
            return validar(result);
        }

        Optional<Usuario> usuarioOptional = usuarioService.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuariodb = usuarioOptional.get();
            if (!usuario.getEmail().isEmpty() && !usuario.getEmail().equalsIgnoreCase(usuariodb.getEmail())
                    && usuarioService.findByEmail(usuario.getEmail()).isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Collections.singletonMap("error", "El email ya existe"));
            }
            usuariodb.setName(usuario.getName());
            usuariodb.setEmail(usuario.getEmail());
            usuariodb.setPassword(usuario.getPassword().toString());
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuariodb));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);
        if (usuarioOptional.isPresent()) {
            usuarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo '" + err.getField() + "' " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

}
