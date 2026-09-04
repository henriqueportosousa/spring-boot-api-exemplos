package br.com.fatec.apiexemplousuario.controller;

import br.com.fatec.apiexemplousuario.model.Usuario;
import br.com.fatec.apiexemplousuario.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    private UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // GET - Listar todos os usuários
    @GetMapping()
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(usuarioService.listar());
    }

    // GET - Buscar usuário por índice
    @GetMapping("/{indice}")
    public ResponseEntity<Usuario> buscar(@PathVariable int indice){
        Usuario usuario = usuarioService.buscarPorIndice(indice);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }

    // POST - adicionar usuário
    @PostMapping
    public ResponseEntity<Usuario> adicionar(@RequestBody Usuario usuario){
        Usuario novoUsuario = usuarioService.adicionar(usuario);

        return ResponseEntity.status(201).body(novoUsuario);
    }

    // PUT - atualizar usuário
    @PutMapping("/{indice}")
    public ResponseEntity<Usuario> atualizar(@PathVariable int indice, @RequestBody Usuario usuario) {
       Usuario usuarioAtulizado = usuarioService.atualizar(indice, usuario);

       if (usuarioAtulizado == null) {
           return ResponseEntity.notFound().build();
       }

       return ResponseEntity.ok(usuarioAtulizado);
    }

    // DELETE - remover usuário
    @DeleteMapping("/{indice}")
    public ResponseEntity<Void> deletar(@PathVariable int indice){
        boolean removido = usuarioService.deletar(indice);

        if(!removido) {
            return ResponseEntity.notFound().build();
        }

        return  ResponseEntity.noContent().build();
    }

}
