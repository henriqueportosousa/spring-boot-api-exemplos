package br.com.fatec.apiexemplousuario.controller;

import br.com.fatec.apiexemplousuario.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private List<Usuario> listaUsuarios= new ArrayList<>();

    @GetMapping()
    public List<Usuario> listar(){
        return listaUsuarios;
    }

    @PostMapping
    public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario){
        listaUsuarios.add(usuario);
        return ResponseEntity.status(201).body(usuario);
    }

    @GetMapping("/{indice}")
    public ResponseEntity<Usuario> buscar(@PathVariable int indice){
        if(indice < 0 || indice >= listaUsuarios.size()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listaUsuarios.get(indice));
    }


    @DeleteMapping("/{indice}")
    public ResponseEntity<Void> deletar(@PathVariable int indice){
        if(indice < 0 || indice >= listaUsuarios.size()){
            return ResponseEntity.notFound().build();
        }
        listaUsuarios.remove(indice);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{indice}")
    public ResponseEntity<Usuario> atualizar(@PathVariable int indice, @RequestBody Usuario usuarioAtualizado) {
        listaUsuarios.set(indice, usuarioAtualizado);
        return ResponseEntity.status(201).body(usuarioAtualizado);
    }
}
