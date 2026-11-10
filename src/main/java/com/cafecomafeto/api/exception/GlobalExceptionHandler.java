
package com.cafecomafeto.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<Map<String, Object>> tratarEmailDuplicado(
            EmailJaCadastradoException exception) {

        Map<String, Object> resposta = Map.of(
                "status", HttpStatus.CONFLICT.value(),
                "mensagem", exception.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(resposta);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<Map<String, Object>> tratarUsuarioNaoEncontrado(
            UsuarioNaoEncontradoException exception) {

        Map<String, Object> resposta = Map.of(
                "status", HttpStatus.NOT_FOUND.value(),
                "mensagem", exception.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(resposta);
    }
}
