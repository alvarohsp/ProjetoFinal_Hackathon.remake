package com.github.alvarohsp.repositorios;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<Usuario> users = Usuario.list("SELECT m FROM Usuario m WHERE m.tipo = 2 ORDER BY id ASC");
        List<UsuarioRetorno> usersFront = new ArrayList<UsuarioRetorno>();
        for(Usuario u : users){
            usersFront.add(new UsuarioRetorno(u.id, u.getNome(), u.getEmail(), u.getIdade(), u.getFormacao(), u.getTipo()));
        }

        return Response.ok().entity(new OkResponse<List<UsuarioRetorno>>(usersFront)).build();
    }

    public Response getUserById(long id){
        Optional<Usuario> buscarUser = Usuario.find("id", id)
                .singleResultOptional();

        if (buscarUser.isEmpty() || buscarUser.get().getTipo() == 1) {
            return Response.status(404).entity(new NotFoundResponse("Não existe usuario com esse id")).build();
        }

        UsuarioRetorno userFront = new UsuarioRetorno();
        userFront.setId(buscarUser.get().id);
        userFront.setNome(buscarUser.get().getNome());
        userFront.setEmail(buscarUser.get().getEmail());
        userFront.setIdade(buscarUser.get().getIdade());
        userFront.setFormacao(buscarUser.get().getFormacao());
        userFront.setTipo(buscarUser.get().getTipo());

        return Response.ok(new OkResponse<UsuarioRetorno>(userFront)).build();
    }

    public Response addUser(Usuario user) {

        Optional<Usuario> verificarUser = Usuario.find("email", user.getEmail())
                .singleResultOptional();

        if (verificarUser.isPresent()) {
            return Response.status(400).entity(new BadRequestResponse("Usuario já existe")).build();
        }

        user.setSenha(Criptografia.criptografarSenha(user.getSenha()));
        user.setTipo(2);


        Usuario.persist(user);
        if (user.isPersistent()) {
            return Response.status(201).entity(new CreatedResponse("Usuario criado com sucesso.")).build();
        }
        return Response.status(400).entity(new BadRequestResponse()).build();

    }
}
