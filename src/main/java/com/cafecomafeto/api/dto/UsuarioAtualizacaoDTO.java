package com.cafecomafeto.api.dto;

public record UsuarioAtualizacaoDTO(
        String nomeCompleto,
        String email,
        String cidade,
        String estado,
        boolean voluntario
) {
}
