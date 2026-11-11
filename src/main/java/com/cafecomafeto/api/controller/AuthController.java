
package com.cafecomafeto.api.controller;

import com.cafecomafeto.api.dto.LoginDTO;
import com.cafecomafeto.api.dto.LoginRespostaDTO;
import com.cafecomafeto.api.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginRespostaDTO login(@RequestBody LoginDTO dados) {
        return authService.autenticar(dados);
    }
}
