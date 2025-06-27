package com.api.devapi.repository;

import com.api.devapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface responsável por manipular a entidade 'Usuario' no banco de dados.
 *
 * @author Gabriel Mathias
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
