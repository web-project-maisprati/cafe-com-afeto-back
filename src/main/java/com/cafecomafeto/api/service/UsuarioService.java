package com.cafecomafeto.api.service;

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
            throw new IllegalArgumentException("Email já cadastrado!");
        }

        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarUsuarioId(Long id) {
        return usuarioRepository.findById(id);
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
