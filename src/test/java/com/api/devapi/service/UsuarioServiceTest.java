package com.api.devapi.service;

import com.api.devapi.model.Usuario;
import com.api.devapi.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Testes Unitários para o UsuarioService.
 *
 * @author Gabriel Mathias
 */
@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    @DisplayName("Teste busca usuário por ID")
    void testBuscaUsuarioPorId() {
        // Preparação
        Usuario mockUsuario = new Usuario();
        mockUsuario.setId(1L);
        mockUsuario.setNome("João");

        when(repository.findById(1L)).thenReturn(Optional.of(mockUsuario));

        // Execução
        Optional<Usuario> result = usuarioService.getUsuarioById(1L);

        // Verificação
        assertTrue(result.isPresent());
        assertEquals("João", result.get().getNome());
        assertNotEquals("Pedro", result.get().getNome());
    }

    @Test
    @DisplayName("Teste busca usuário por nome")
    void testBuscaUsuariosPorNome() {
        // Preparação
        String nome = "Paul";
        Pageable pageable = PageRequest.of(0, 10);

        Usuario usr1 = new Usuario();
        usr1.setNome("Paula Vitória");
        Usuario usr2 = new Usuario();
        usr2.setNome("João Paulo Andrade");

        Page<Usuario> retornoEsperado = new PageImpl<>(List.of(usr1, usr2));

        when(repository.findByNomeContainingIgnoreCase(nome, pageable)).thenReturn(retornoEsperado);

        // Execução
        Page<Usuario> resultado = usuarioService.getUsuarioByNome(nome, pageable);

        // Verificação
        assertEquals(2, resultado.getContent().size());
        assertEquals("Paula Vitória", resultado.getContent().get(0).getNome());
        verify(repository, times(1)).findByNomeContainingIgnoreCase(nome, pageable);
    }

    @Test
    @DisplayName("Teste salvar usuários com falha no envio de email")
    public void testCreateUsuario_EnviaEmailFalha() {
        // Preparação
        Usuario usuario = new Usuario();
        usuario.setNome("Paulo");
        usuario.setEmail("paulo@cmd.com");

        Usuario usuarioSalvo = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Paulo");
        usuario.setEmail("paulo@cmd.com");

        when(repository.save(usuario)).thenReturn(usuarioSalvo);

        // Execução
        lenient().doThrow(new RuntimeException("Erro ao enviar e-mail"))
                .when(emailService).enviarEmailUsuario("I", usuarioSalvo);// Lança exception para simular falha

        // Verificação
        RuntimeException ex = assertThrows(RuntimeException.class, () -> usuarioService.createUsuario(usuario));
        assertEquals("Usuário salvo, mas houve falha ao enviar o e-mail.", ex.getMessage());
        verify(repository).save(usuario);
    }
}
