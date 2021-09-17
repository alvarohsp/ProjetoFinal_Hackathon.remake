package com.github.alvarohsp.entidades;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;

@Entity
@Table(name = "cursos")
public class Curso extends PanacheEntity {

    private String nome;
    private String descricao;
    @ManyToOne
    private Usuario professor;

    private ArrayList<Aula> aula;

    public Curso() {

    }

    public Curso(String nome, String descricao, Usuario professor, ArrayList<Aula> aula) {
        this.nome = nome;
        this.descricao = descricao;
        this.professor = professor;
        this.aula = aula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getProfessor() {
        return professor;
    }

    public void setProfessor(Usuario professor) {
        this.professor = professor;
    }

    public ArrayList<Aula> getAula() {
        return aula;
    }

    public void setAula(ArrayList<Aula> aula) {
        this.aula = aula;
    }
}
