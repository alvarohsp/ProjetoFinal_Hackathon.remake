package com.github.alvarohsp.repositorios;

import com.github.alvarohsp.entidades.Professor;
import com.github.alvarohsp.entidades.UsuarioRetorno;
import com.github.alvarohsp.responses.NotFoundResponse;
import com.github.alvarohsp.responses.OkResponse;
import com.github.alvarohsp.utilitarios.Criptografia;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProfessorRepositorio {

    public Response addProf(Professor user) {

        Optional<Professor> buscarUser = Professor.find("email", user.getEmail())
                .singleResultOptional();

        if (buscarUser.isPresent()) {
            return Response.status(400).entity("Email já cadastrado!").build();
        }

        user.setSenha(Criptografia.criptografarSenha(user.getSenha()));
        user.setTipo(1);

        Professor.persist(user);
        if (user.isPersistent()) {
            return Response.status(201).entity("Usuario Administrador: " + user.getNome() + " criado com sucesso.").build();
        }
        return Response.status(400).build();

    }

    public Response getAll(){
        List<Professor> users = Professor.list("SELECT m FROM Professor m WHERE m.tipo = 1 ORDER BY id ASC");

        return Response.ok(new OkResponse<>(users)).build();
    }

    public Response getProfById(long id){
        Optional<Professor> buscaUser = Professor.find("id", id)
                .singleResultOptional();

        if (buscaUser.isEmpty() || buscaUser.get().getTipo() != 1) {
            return Response.status(404).entity(new NotFoundResponse()).build();
        }

        return Response.ok(buscaUser.get()).build();
    }
}
