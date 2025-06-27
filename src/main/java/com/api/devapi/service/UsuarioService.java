package com.api.devapi.service;

import com.api.devapi.model.Usuario;
import org.springframework.stereotype.Service;
import com.api.devapi.repository.UsuarioRepository;

import java.util.List;

/**
 * Serviço responsável por gerenciar as operações de manipulação do objeto 'Usuario' na base.
 *
 * @author Gabriel Mathias
 */
@Service
public class UsuarioService {
    // Referencia o Gerenciador da entidade 'Usuario'.
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    /**
     * Retorna todos os usuários na base.
     *
     * @return
     */
    public List<Usuario> getTodosUsuarios() {
        return repository.findAll();
    }

    /**
     * Registra o usuário na base.
     *
     * @param usuario
     * @return
     */
    public Usuario createUsuario(Usuario usuario) {
        return repository.save(usuario);
    }
}