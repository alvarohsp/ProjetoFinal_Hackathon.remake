package com.github.alvarohsp.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Usuario extends PanacheEntity {

    @Column(length = 50)
    private String nome;

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnore
    private String senha;

    @Column(length = 128, unique = true)
    private String email;

    @Column(updatable = false)
    private Integer tipo; // 1 = PROFESSOR  2 = ALUNO

    public Usuario(String nome, String senha, String email, Integer tipo) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.tipo = tipo;
    }

    public Usuario() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
}
