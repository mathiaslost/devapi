package com.api.devapi.service;

import com.api.devapi.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.api.devapi.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

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
     * Registra o usuário na base.
     *
     * @param usuario
     * @return
     */
    public Usuario createUsuario(Usuario usuario) {
        return repository.save(usuario);
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
     * Busca usuários a partir de string parcial do nome.
     *
     * @param nome
     * @param pageable
     * @return
     */
    public Page<Usuario> getUsuarioByNome(String nome, Pageable pageable) {
        return repository.findByNomeContainingIgnoreCase(nome, pageable);
    }

    /**
     * Busca um usuário a partir do ID.
     *
     * @param id
     * @return
     */
    public Optional<Usuario> getUsuarioById(Long id) {
        return repository.findById(id);
    }
}