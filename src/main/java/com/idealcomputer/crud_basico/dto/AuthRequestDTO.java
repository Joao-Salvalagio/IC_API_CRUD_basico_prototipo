package com.idealcomputer.crud_basico.dto;

import lombok.Data;

@Data
public class AuthRequestDTO {

    private String name;
    private String email;
    private String password;
}