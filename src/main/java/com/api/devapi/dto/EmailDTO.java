package com.api.devapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para manipulação dos dados de Email.
 *
 * @author Gabriel Mathias
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO {
    private String remetente;
    private String destinatario;
    private String titulo;
    private String corpo;
}
