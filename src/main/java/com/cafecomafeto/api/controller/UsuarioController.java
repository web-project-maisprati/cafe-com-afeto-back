package com.cafecomafeto.api.controller;

import com.cafecomafeto.api.dto.UsuarioAtualizacaoDTO;
import com.cafecomafeto.api.dto.UsuarioCadastroDTO;
import com.cafecomafeto.api.dto.UsuarioRespostaDTO;
import com.cafecomafeto.api.model.Usuario;
import com.cafecomafeto.api.service.UsuarioService;
import jdk.jfr.Frequency;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public UsuarioRespostaDTO cadastrarUsuario(@RequestBody UsuarioCadastroDTO dados) {
        Usuario usuario = new Usuario();

        usuario.setNomeCompleto(dados.nomeCompleto());
        usuario.setEmail(dados.email());
        usuario.setSenha(dados.senha());
        usuario.setCidade(dados.cidade());
        usuario.setEstado(dados.estado());
        usuario.setTipoUsuario(dados.tipoUsuario());
        usuario.setVoluntario(dados.voluntario());

        Usuario usuarioSalvo = usuarioService.cadastrarUsuario(usuario);

        return new UsuarioRespostaDTO(
                usuarioSalvo.getId(),
                usuarioSalvo.getNomeCompleto(),
                usuarioSalvo.getEmail(),
                usuarioSalvo.getCidade(),
                usuarioSalvo.getEstado(),
                usuarioSalvo.getTipoUsuario(),
                usuarioSalvo.isVoluntario(),
                usuarioSalvo.getCriadoEm()
        );
    }

    @GetMapping("/{id}")
    public UsuarioRespostaDTO buscarUsuarioId(@PathVariable Long id) {

        Usuario usuario = usuarioService.buscarUsuarioId(id);

        return new UsuarioRespostaDTO(
                usuario.getId(),
                usuario.getNomeCompleto(),
                usuario.getEmail(),
                usuario.getCidade(),
                usuario.getEstado(),
                usuario.getTipoUsuario(),
                usuario.isVoluntario(),
                usuario.getCriadoEm()
        );
    }

    @GetMapping("")
    public List<UsuarioRespostaDTO> listarUsuarios(){

        return usuarioService.listarUsuarios()
                .stream()
                .map(usuario -> new UsuarioRespostaDTO(
                        usuario.getId(),
                        usuario.getNomeCompleto(),
                        usuario.getEmail(),
                        usuario.getCidade(),
                        usuario.getEstado(),
                        usuario.getTipoUsuario(),
                        usuario.isVoluntario(),
                        usuario.getCriadoEm()
                ))
                .toList();
    }

    @PutMapping("/{id}")
    public UsuarioRespostaDTO atualizarUsuario(@PathVariable Long id, @RequestBody UsuarioAtualizacaoDTO dados) {

        Usuario usuarioSalvo = usuarioService.atualizarUsuario(id, dados);

        return new UsuarioRespostaDTO(
                usuarioSalvo.getId(),
                usuarioSalvo.getNomeCompleto(),
                usuarioSalvo.getEmail(),
                usuarioSalvo.getCidade(),
                usuarioSalvo.getEstado(),
                usuarioSalvo.getTipoUsuario(),
                usuarioSalvo.isVoluntario(),
                usuarioSalvo.getCriadoEm()
        );
    }

    @DeleteMapping("/{id}")
    public void excluirUsuario(@PathVariable Long id){
        usuarioService.excluirUsuario(id);
    }
}