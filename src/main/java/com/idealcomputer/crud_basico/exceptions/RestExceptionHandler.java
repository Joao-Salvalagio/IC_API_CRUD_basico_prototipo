package com.idealcomputer.crud_basico.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    /**
     * Captura erros de violação de integridade do banco (ex: e-mail único).
     * Retorna: 409 Conflict
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String errorMessage = "O e-mail fornecido já está em uso.\nPor favor, utilize outro e-mail.";
        Map<String, String> errorResponse = Map.of("message", errorMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    /**
     * NOVO MANIPULADOR
     * Captura nossas exceções de lógica de negócio personalizadas (que estamos
     * lançando como RuntimeException).
     * Retorna: 400 Bad Request (uma requisição inválida, como um orçamento muito baixo)
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleBusinessException(RuntimeException ex) {
        // Pega a mensagem de erro que definimos (ex: "Orçamento insuficiente...")
        String errorMessage = ex.getMessage();

        Map<String, String> errorResponse = Map.of("message", errorMessage);

        // Retorna o status 400 Bad Request, que é mais apropriado para um erro de lógica de negócio.
        // O frontend agora pode ler esta mensagem!
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}