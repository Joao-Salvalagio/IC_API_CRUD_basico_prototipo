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

    @ExceptionHandler(DataIntegrityViolationException.class)//criando excecao de erro para email ja em uso
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {

        String errorMessage = "O e-mail fornecido já está em uso.\nPor favor, utilize outro e-mail.";

        Map<String, String> errorResponse = Map.of("message", errorMessage);

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT); //retorna o status 409 conflict para que o front entenda
    }
}