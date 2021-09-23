package com.github.alvarohsp.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name = "aulas")
public class Aula extends PanacheEntity {

    private String nome;
    private Integer duracao;
    private ArrayList<String> topicos;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    @JsonBackReference
    private Curso curso;

    public Aula(String nome, Integer duracao, ArrayList<String> topicos, Curso curso) {
        this.nome = nome;
        this.duracao = duracao;
        this.topicos = topicos;
        this.curso = curso;
    }

    public Aula() {
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
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

    public ArrayList<String> getTopicos() {
        return topicos;
    }

    public void setTopicos(ArrayList<String> topicos) {
        this.topicos = topicos;
    }

}

