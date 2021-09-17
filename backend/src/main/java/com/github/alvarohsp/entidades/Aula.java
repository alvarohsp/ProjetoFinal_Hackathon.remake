package com.github.alvarohsp.entidades;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;

@Entity
@Table(name = "aulas")
public class Aula extends PanacheEntity {

    private String nome;
    private Integer duracao;

    @ManyToOne
    private Curso curso;
    private ArrayList<String> topicos;

    public Aula(String nome, Integer duracao, Curso curso, ArrayList<String> topicos) {
        this.nome = nome;
        this.duracao = duracao;
        this.curso = curso;
        this.topicos = topicos;
    }

    public Aula() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public ArrayList<String> getTopicos() {
        return topicos;
    }

    public void setTopicos(ArrayList<String> topicos) {
        this.topicos = topicos;
    }
}

