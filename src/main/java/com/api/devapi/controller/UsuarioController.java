package com.api.devapi.controller;

import com.api.devapi.dto.TrocaSenhaRequestDTO;
import com.api.devapi.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.api.devapi.service.UsuarioService;

import java.util.List;
import java.util.Optional;

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
     * Cria um usuário no banco de dados.
     *
     * @param usuario
     * @return
     */
    @PostMapping("/createUsuario")
    public Usuario create(@RequestBody Usuario usuario) {
        return service.createUsuario(usuario);
    }

    /**
     * Atualiza um usuário existinte com base no ID.
     *
     * @param usuario
     * @return
     */
    @PostMapping("/updateUsuario")
    public Usuario update(@RequestBody Usuario usuario) {
        return service.updateUsuario(usuario.getId(), usuario);
    }

    /**
     * Atualiza senha na base de dados.
     *
     * @param id
     * @param request
     * @return
     */
    @PutMapping("/{id}/password")
    public String changePassword(@PathVariable Long id, @RequestBody TrocaSenhaRequestDTO request) {
        return service.updatePassword(id, request);
    }

    /**
     * Obtém todos os usuários.
     *
     * @return
     */
    @GetMapping("/getTodosUsuarios")
    public List<Usuario> getAll() {
        return service.getTodosUsuarios();
    }

    /**
     * Busca usuário por nome com paginação.
     *
     * @param nome
     * @return
     */
    @GetMapping("/getUsuarioByNome")
    public Page<Usuario> getUsuarioByNome(
            @RequestParam String nome,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        return service.getUsuarioByNome(nome, pageable);
    }

    /**
     * Busca usuário a partir do Id.
     *
     * @param id
     * @return
     */
    @GetMapping("/getUsuarioById")
    public ResponseEntity<Usuario> getUsuarioById(@RequestParam Long id) {
        Optional<Usuario> usuario = service.getUsuarioById(id);

        // Verifica se foi localizado um usuário com aquele ID.
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.get());
        } else {
            // Se não localizar retorna status 404.
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
