package com.idealcomputer.crud_basico.models;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_Usuarios")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "Nome",  nullable = false)
    private String name;
    @Column(name = "Email",  nullable = false, unique = true)
    private String email;
    @Column(name = "Funcao",  nullable = false)
    private String function;

    public UserModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
