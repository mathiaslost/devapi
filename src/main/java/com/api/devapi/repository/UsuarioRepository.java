package com.api.devapi.repository;

import com.api.devapi.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Interface responsável por manipular a entidade 'Usuario' no banco de dados.
 *
 * @author Gabriel Mathias
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    /**
     * Busca de usuário a partir do nome e retorna com paginação.
     *
     * @param nome
     * @param pageable
     * @return
     */
    Page<Usuario> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
