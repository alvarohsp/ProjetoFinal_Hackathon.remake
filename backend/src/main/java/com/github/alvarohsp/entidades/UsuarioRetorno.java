package com.github.alvarohsp.entidades;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UsuarioRetorno {
    private long id;
    private String nome;
    private String email;
    private String formacao;
    private Integer idade;
    private Integer tipo;

    public UsuarioRetorno(long id, String name, String email, Integer idade){
        this.id = id;
        this.nome = name;
        this.email = email;
        this.idade = idade;
    }

    public UsuarioRetorno(){

    }

    public long getId() {
        return id;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getFormacao() {
//        return formacao;
//    }
//
//    public void setFormacao(String formacao) {
//        this.formacao = formacao;
//    }
//
//    public Integer getIdade() {
//        return idade;
//    }
//
//    public void setIdade(Integer idade) {
//        this.idade = idade;
//    }
}
