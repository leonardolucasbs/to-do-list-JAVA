package com.leonardolucs.todolist.controllers;

import com.leonardolucs.todolist.models.dto.UsuarioDTO;
import com.leonardolucs.todolist.models.dto.UsuarioPublicoDTO;
import com.leonardolucs.todolist.models.entities.Usuario;
import com.leonardolucs.todolist.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuariosController {
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> createUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO UsuarioDTO = null;
        usuarioService.createUsuario(usuarioDTO);
        return ResponseEntity.status(201).build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deketeUsuario(@PathVariable Long id){
        usuarioService.deletaUsuario(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios(){
        return ResponseEntity.ok(usuarioService.getAllUsuariosEntities());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizaUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO){
        return usuarioService.atualizaUsuario(id, usuarioDTO);
        //atualizar o usuario
    }


}
