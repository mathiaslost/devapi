package com.api.devapi.service;

import com.api.devapi.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testes unitários para o EmailService.
 *
 * @author Gabriel Mathias
 */
class EmailServiceTest {

    private EmailService emailService;

    @BeforeEach
    void setUp() {
        emailService = new EmailService();
    }

    @Test
    @DisplayName("Teste mensagem de sucesso no console")
    void testMensagemDeSucessoNoConsole() {
        // Preparação
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        emailService = new EmailService();

        Usuario usuario = new Usuario();
        usuario.setNome("Gabriel");
        usuario.setEmail("gabriel@dev.com");
        usuario.setSenha("teste@123");

        // Execução
        emailService.enviarEmailUsuario("I", usuario);

        // Verificação
        String saida = output.toString().trim();
        assertTrue(saida.contains("Email enviado com sucesso!"));
        System.setOut(System.out);
    }
}