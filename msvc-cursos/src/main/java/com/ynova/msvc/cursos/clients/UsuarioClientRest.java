package com.ynova.msvc.cursos.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ynova.msvc.cursos.models.Usuario;

@FeignClient(name = "msvc-usuarios", url = "localhots:8001")
public interface UsuarioClientRest {

    @GetMapping("/usuarios/{id}")
    Usuario buscar(@PathVariable Long id);

    @PostMapping("/usuarios/save")
    Usuario guardar(@RequestBody Usuario usuario);

}
