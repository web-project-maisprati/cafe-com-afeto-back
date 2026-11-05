package com.cafecomafeto.api.dto;

public record UsuarioCadastroDTO (
    String nomeCompleto,
    String email,
    String senha,
    String cidade,
    String estado,
    String tipoUsuario,
    boolean voluntario
) {}
