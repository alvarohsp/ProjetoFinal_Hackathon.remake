package com.github.alvarohsp.entidades;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario extends PanacheEntity{

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(nullable = false)
    private String senha;

    @Column(length = 128, nullable = false, unique = true)
    private String email;

    @Column(updatable = false, nullable = false)
    private Integer tipo; // 1 = PROFESSOR  2 = ALUNO

    private Integer idade;

    private String formacao;

    public Usuario(String nome, String senha, String email, Integer tipo) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.tipo = tipo;
    }

    public Usuario(String nome, String senha, String email, Integer tipo, Integer idade, String formacao) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.tipo = tipo;
        this.idade = idade;
        this.formacao = formacao;
    }

    public Usuario() {
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
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
