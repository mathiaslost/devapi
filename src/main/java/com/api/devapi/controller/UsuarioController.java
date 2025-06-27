package com.api.devapi.controller;

import com.api.devapi.model.Usuario;
import org.springframework.web.bind.annotation.*;
import com.api.devapi.service.UsuarioService;

import java.util.List;

/**
 * Classe responsável por gerenciar as requisições de manipulação de usuários.
 *
 * @author Gabriel Mathias
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    /**
     * Obtém todos os usuários.
     *
     * @return
     */
    @GetMapping
    public List<Usuario> getAll() {
        return service.getTodosUsuarios();
    }

    /**
     * Cria um usuário no banco de dados.
     *
     * @param usuario
     * @return
     */
    @PostMapping
    public Usuario create(@RequestBody Usuario usuario) {
        return service.createUsuario(usuario);
    }
}
