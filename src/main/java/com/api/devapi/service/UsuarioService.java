package com.api.devapi.service;

import com.api.devapi.dto.TrocaSenhaRequestDTO;
import com.api.devapi.model.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.api.devapi.repository.UsuarioRepository;
import org.springframework.util.StringUtils;

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

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registra o usuário na base.
     *
     * @param usuario
     * @return
     */
    @Transactional
    public Usuario createUsuario(Usuario usuario) {
        // Criptografa a senha com Hash.
        String hashedPassword = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(hashedPassword);

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

        // Antes de adicionar cada campo editado, verifica se foi recebido no json.
        if (StringUtils.hasText(usuarioEdit.getNome())) {
            usuarioExist.setNome(usuarioEdit.getNome());
        }
        if (StringUtils.hasText(usuarioEdit.getUsername())) {
            usuarioExist.setUsername(usuarioEdit.getUsername());
        }
        if (StringUtils.hasText(usuarioEdit.getEmail())) {
            usuarioExist.setEmail(usuarioEdit.getEmail());
        }

        Usuario usuarioAtualizado = repository.save(usuarioExist);
        emailService.enviarEmailUsuario("U", usuarioAtualizado);
        return usuarioAtualizado;
    }

    /**
     * Verifica e atualiza senha do usuário.
     *
     * @param id
     * @param request
     * @return
     */
    public String updatePassword(Long id, TrocaSenhaRequestDTO request) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new RuntimeException(("Usuário não encontrado.")));

        // Verifica se a senha atual está correta.
        if (!passwordEncoder.matches(request.getSenhaAtual(), usuario.getSenha())) {
            return "Senha atual incorreta.";
        }

        // Aplica o encoding com o hash na nova senha.
        String novaSenhaHash = passwordEncoder.encode(request.getNovaSenha());
        usuario.setSenha(novaSenhaHash);
        repository.save(usuario);

        return "Senha atualizada com sucesso.";
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