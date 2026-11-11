
package com.cafecomafeto.api.service;

import com.cafecomafeto.api.dto.LoginDTO;
import com.cafecomafeto.api.dto.LoginRespostaDTO;
import com.cafecomafeto.api.model.Usuario;
import com.cafecomafeto.api.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginRespostaDTO autenticar(LoginDTO dados) {

        Usuario usuario = usuarioRepository.findByEmail(dados.email())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "E-mail ou senha inválidos!"
                ));

        if (!passwordEncoder.matches(dados.senha(), usuario.getSenha())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "E-mail ou senha inválidos!"
            );
        }

        String token = jwtService.gerarToken(usuario.getEmail());

        return new LoginRespostaDTO(token, "Bearer");
    }
}
