package com.api.devapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrocaSenhaRequestDTO {
    private String senhaAtual;
    private String novaSenha;
}
