package com.ynova.msvc.usuarios.msvc_usuarios.controller;

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

import com.ynova.msvc.usuarios.msvc_usuarios.model.entity.Usuario;
import com.ynova.msvc.usuarios.msvc_usuarios.services.UsuarioService;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping({"/usuarios","/"})
    public List<Usuario> listar(){
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id){
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);
        if (usuarioOptional.isPresent()){
            return ResponseEntity.ok().body(usuarioOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /*
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario guardar(@RequestBody Usuario usuario ){
        return usuarioService.save(usuario);
    }
 */
    @PostMapping("/save")
    public ResponseEntity<?> guardar(@RequestBody Usuario usuario ){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Usuario usuario,@PathVariable Long id ){
        Optional<Usuario> usuarioOptional =  usuarioService.findById(id);

        if(usuarioOptional.isPresent()){
            Usuario usuariodb = usuarioOptional.get();
            usuariodb.setName(usuario.getName());
            usuariodb.setEmail(usuario.getEmail());
            usuariodb.setPassword(usuario.getPassword().toString());
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuariodb));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
       Optional<Usuario> usuarioOptional = usuarioService.findById(id);
       if(usuarioOptional.isPresent()){
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
       }
       return ResponseEntity.notFound().build();
    }


}
