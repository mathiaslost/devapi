package com.api.devapi.service;

import com.api.devapi.model.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private EmailService emailService;

    /**
     * Registra o usuário na base.
     *
     * @param usuario
     * @return
     */
    @Transactional
    public Usuario createUsuario(Usuario usuario) {
        // Salva o registro na base.
        Usuario usuarioSalvo = repository.save(usuario);

        try {
            // Envia e-mail para o usuário.
            emailService.enviarEmailUsuario("I", usuarioSalvo);
        } catch (Exception e) {
            throw new RuntimeException("Usuário salvo, mas houve falha ao enviar o e-mail.");
        }
        return usuarioSalvo;
    }

    /**
     * Atualiza o usuário na base.
     *
     * @param id
     * @param usuarioEdit
     * @return
     */
    @Transactional
    public Usuario updateUsuario(Long id, Usuario usuarioEdit) {
        Usuario usuarioExist = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não foi possível localizar o usuário."));

        usuarioExist.setNome(usuarioEdit.getNome());
        usuarioExist.setEmail(usuarioEdit.getEmail());

        Usuario usuarioAtualizado = repository.save(usuarioExist);
        emailService.enviarEmailUsuario("U", usuarioAtualizado);
        return usuarioAtualizado;
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