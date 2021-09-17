package com.github.alvarohsp.repositorios;

import com.github.alvarohsp.entidades.Curso;
import com.github.alvarohsp.entidades.Usuario;
import com.github.alvarohsp.entidades.UsuarioRetorno;
import com.github.alvarohsp.responses.BadRequestResponse;
import com.github.alvarohsp.responses.CreatedResponse;
import com.github.alvarohsp.responses.OkResponse;
import com.github.alvarohsp.utilitarios.Criptografia;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CursoRepositorio {

    public Response getAll() {
        List<Curso> cursos = Curso.list("SELECT m FROM Curso m ORDER BY id ASC");
        return Response.ok().entity(new OkResponse<List<Curso>>(cursos)).build();
    }

    public Response addCurso(Curso curso) {
        Optional<Curso> verificarCurso = Curso.find("nome", curso.getNome())
                .singleResultOptional();

        if (verificarCurso.isPresent()) {
            return Response.status(400).entity(new BadRequestResponse("Curso já existe")).build();
        }

//        Optional<Usuario> buscaDoProf = Usuario.find("id", curso.getProfessor()).singleResultOptional();
//
//        curso.setProfessor(buscaDoProf.get());

        Curso.persist(curso);
        if (curso.isPersistent()) {
            return Response.status(201).entity(new CreatedResponse("Curso criado com sucesso.")).build();
        }
        return Response.status(400).entity(new BadRequestResponse()).build();
    }

}
