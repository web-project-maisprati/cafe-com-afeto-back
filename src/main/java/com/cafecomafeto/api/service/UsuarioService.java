package com.cafecomafeto.api.service;

import com.cafecomafeto.api.exception.EmailJaCadastradoException;
import com.cafecomafeto.api.exception.UsuarioNaoEncontradoException;
import com.cafecomafeto.api.model.Usuario;
import com.cafecomafeto.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastrarUsuario(Usuario usuario) {

        if (verificarEmail(usuario.getEmail())) {
            throw new EmailJaCadastradoException("Email já cadastrado!");
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario buscarUsuarioId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(
                        "Usuário não encontrado!"
                ));
    }

    public boolean usuarioExiste(Long id) {
        return usuarioRepository.existsById(id);
    }

    public void excluirUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public boolean verificarEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }
}
