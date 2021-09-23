package com.github.alvarohsp.entidades;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cursos")
public class Curso extends PanacheEntity {

    private String nome;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @OneToMany(mappedBy = "curso")
    @JsonManagedReference
    private List<Aula> aulas;

    @ManyToMany
    @JoinTable(name = "curso_aluno",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id"))
    private List<Aluno> alunos;

    public Curso() {
    }

    public Curso(String nome, String descricao, Professor professor, List<Aula> aulas, List<Aluno> alunos) {
        this.nome = nome;
        this.descricao = descricao;
        this.professor = professor;
        this.aulas = aulas;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
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

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", professor=" + professor +
                ", aulas=" + aulas +
                ", alunos=" + alunos +
                '}';
    }
}
