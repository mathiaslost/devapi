package com.api.devapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe modelo para Representação do Objeto de Email.
 *
 * @author Gabriel Mathias
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Email {
    private String remetente;
    private String destinatario;
    private String titulo;
    private String corpo;
}
