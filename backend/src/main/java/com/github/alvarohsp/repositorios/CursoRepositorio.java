package com.github.alvarohsp.repositorios;

import com.github.alvarohsp.entidades.*;
import com.github.alvarohsp.responses.BadRequestResponse;
import com.github.alvarohsp.responses.CreatedResponse;
import com.github.alvarohsp.responses.OkResponse;
import com.github.alvarohsp.utilitarios.Criptografia;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.util.*;

@ApplicationScoped
public class CursoRepositorio {

    public Response getAll() {
        List<Curso> cursos = Curso.list("SELECT m FROM Curso m ORDER BY id ASC");

        cursos.forEach(System.out::println);

        return Response.ok().entity(new OkResponse<List<Curso>>(cursos)).build();
    }

    public Response addCurso(Curso curso) {
        Optional<Curso> verificarCurso = Curso.find("nome", curso.getNome())
                .singleResultOptional();

        if (verificarCurso.isPresent()) {
            return Response.status(400).entity(new BadRequestResponse("Curso já existe")).build();
        }

        Optional<Professor> buscaDoProf = Professor.find("id", curso.getProfessor().id).singleResultOptional();

        if (buscaDoProf.isEmpty() || buscaDoProf.get().getTipo() != 1) {
            return Response.status(400).entity(new BadRequestResponse("Professor inválido")).build();
        }

        //////////////////////////////////////////////
        curso.setProfessor(buscaDoProf.get());

        System.out.println(curso);

        Aula aulinha = new Aula();
        aulinha.setNome(curso.getAulas().get(0).getNome());
        aulinha.setDuracao(curso.getAulas().get(0).getDuracao());
        aulinha.setTopicos(curso.getAulas().get(0).getTopicos());
        curso.setAulas(List.of(aulinha));
        aulinha.setCurso(curso);

        Curso.persist(curso);
        Aula.persist(aulinha);

        if (curso.isPersistent()) {
            return Response.status(201).entity(new CreatedResponse("Curso criado com sucesso.")).build();
        }
        return Response.status(400).entity(new BadRequestResponse("Erro na criação")).build();
    }

}
