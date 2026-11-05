package com.cafecomafeto.api.dto;

import java.time.OffsetDateTime;

public record UsuarioRespostaDTO (
        Long id,
        String nomeCompleto,
        String email,
        String cidade,
        String estado,
        String tipoUsuario,
        boolean voluntario,
        OffsetDateTime criadoEm
) {}
