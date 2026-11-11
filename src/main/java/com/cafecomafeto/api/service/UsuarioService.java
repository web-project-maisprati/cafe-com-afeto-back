package com.cafecomafeto.api.service;

import com.cafecomafeto.api.dto.UsuarioAtualizacaoDTO;
import com.cafecomafeto.api.exception.EmailJaCadastradoException;
import com.cafecomafeto.api.exception.UsuarioNaoEncontradoException;
import com.cafecomafeto.api.model.Usuario;
import com.cafecomafeto.api.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario cadastrarUsuario(Usuario usuario) {

        if (verificarEmail(usuario.getEmail())) {
            throw new EmailJaCadastradoException("Email já cadastrado!");
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

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
        buscarUsuarioId(id);

        usuarioRepository.deleteById(id);
    }

    public boolean verificarEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario atualizarUsuario(Long id, UsuarioAtualizacaoDTO dados) {
        Usuario usuario = buscarUsuarioId(id);

        if(verificarEmail(dados.email()) && !dados.email().equals(usuario.getEmail())) {
            throw new EmailJaCadastradoException("Email já cadastrado!");
        }

        usuario.setNomeCompleto(dados.nomeCompleto());
        usuario.setEmail(dados.email());
        usuario.setCidade(dados.cidade());
        usuario.setEstado(dados.estado());
        usuario.setVoluntario(dados.voluntario());

        return usuarioRepository.save(usuario);
    }
}
