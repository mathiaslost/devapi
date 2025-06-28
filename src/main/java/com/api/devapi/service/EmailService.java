package com.api.devapi.service;

import com.api.devapi.model.Email;
import com.api.devapi.model.Usuario;
import org.springframework.stereotype.Service;

/**
 * Serviço responsável por gerenciar as operações de envio de e-mail.
 *
 * @author Gabriel Mathias
 */
@Service
public class EmailService {

    /**
     * Envia e-mail para o usuário com as informações de acesso.
     *
     * @param operacao "I" para inclusão, "U" para atualização
     * @param usuario Usuario que receberá o e-mail
     */
    public void enviarEmailUsuario(String operacao, Usuario usuario) {
        // Título.
        String assunto = (operacao.equals("U")) ? "O seu usuário foi atualizado!" : "O seu usuário foi criado!";

        // Corpo.
        StringBuilder corpo = new StringBuilder();
        corpo.append("Olá, ").append(usuario.getNome()).append("!").append("<br />");
        corpo.append(assunto).append(" Aqui estão suas informações de acesso:").append("<br />");
        corpo.append("Email: ").append(usuario.getNome()).append("<br />");
        corpo.append("Nome: ").append(usuario.getNome()).append("<br />");
        corpo.append("Senha: ").append(usuario.getSenha()).append("<br />");

        // Preparando email para o envio.
        Email email = new Email();
        email.setRemetente("email@corporativo.com.br");
        email.setDestinatario(usuario.getEmail());
        email.setTitulo(assunto);
        email.setCorpo(corpo.toString());

        // Simulação de envio.
        System.out.println("Email enviado com sucesso!");
    }
}
