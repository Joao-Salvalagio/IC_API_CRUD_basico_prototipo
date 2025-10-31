package com.idealcomputer.crud_basico.enums;

/**
 * Define as "Funções" (Roles) de permissão no sistema.
 */
public enum UserRole {
    USUARIO,       // Cliente padrão, pode gerar e salvar builds
    ADMINISTRADOR  // Acesso total, pode gerenciar hardware e usuários
}