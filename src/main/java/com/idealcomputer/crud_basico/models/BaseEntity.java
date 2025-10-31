package com.idealcomputer.crud_basico.models;

// Esta interface genérica garante que qualquer classe que a implemente
// terá os métodos getId() e setId().
public interface BaseEntity<ID> {
    ID getId();
    void setId(ID id);
}