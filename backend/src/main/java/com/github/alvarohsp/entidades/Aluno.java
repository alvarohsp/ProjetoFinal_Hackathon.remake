package com.github.alvarohsp.entidades;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class Aluno extends Usuario{


    private Integer idade;
    private String formacao;

    @ManyToMany
    private List<Curso> cursos;

    public Aluno() {
    }

    public Aluno(String nome, String senha, String email, Integer tipo, Integer idade, String formacao) {
        super(nome, senha, email, tipo);
        this.idade = idade;
        this.formacao = formacao;
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
}
