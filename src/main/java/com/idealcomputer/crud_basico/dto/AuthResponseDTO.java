package com.idealcomputer.crud_basico.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {

    private String token;
    private String email;
    private String name;

    public AuthResponseDTO(String token, String email, String name) {
        this.token = token;
        this.email = email;
        this.name = name;
    }
}