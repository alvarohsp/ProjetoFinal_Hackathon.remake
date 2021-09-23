package com.github.alvarohsp.repositorios;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.github.alvarohsp.entidades.Aluno;
import com.github.alvarohsp.entidades.UsuarioRetorno;
import com.github.alvarohsp.entidades.Usuario;
import com.github.alvarohsp.responses.BadRequestResponse;
import com.github.alvarohsp.responses.CreatedResponse;
import com.github.alvarohsp.responses.NotFoundResponse;
import com.github.alvarohsp.responses.OkResponse;
import com.github.alvarohsp.utilitarios.*;

@ApplicationScoped
public class AlunoRepositorio {

    public Response getAll(){
        List<Aluno> users = Aluno.list("SELECT m FROM Aluno m WHERE m.tipo = 2 ORDER BY id ASC");
        return Response.ok().entity(new OkResponse<>(users)).build();
    }

    public Response getAlunoById(long id){
        Optional<Aluno> buscarUser = Aluno.find("id", id)
                .singleResultOptional();

        if (buscarUser.isEmpty() || buscarUser.get().getTipo() == 1) {
            return Response.status(404).entity(new NotFoundResponse("Não existe usuario com esse id")).build();
        }

        return Response.ok(new OkResponse<Usuario>(buscarUser.get())).build();
    }

    public Response addAluno(Usuario user) {

        Optional<Aluno> verificarUser = Usuario.find("email", user.getEmail())
                .singleResultOptional();

        if (verificarUser.isPresent()) {
            return Response.status(400).entity(new BadRequestResponse("Usuario já existe")).build();
        }

        user.setSenha(Criptografia.criptografarSenha(user.getSenha()));
        user.setTipo(2);


        Aluno.persist(user);
        if (user.isPersistent()) {
            return Response.status(201).entity(new CreatedResponse("Usuario criado com sucesso.")).build();
        }
        return Response.status(400).entity(new BadRequestResponse()).build();

    }
}
