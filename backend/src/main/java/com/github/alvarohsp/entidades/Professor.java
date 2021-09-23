package com.github.alvarohsp.entidades;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Professor extends Usuario{

    @OneToMany(mappedBy = "professor")
    private List<Curso> cursosLecionados;

    public Professor(String nome, String senha, String email, Integer tipo) {
        super(nome, senha, email, tipo);
    }

    public Professor() {
    }
}
