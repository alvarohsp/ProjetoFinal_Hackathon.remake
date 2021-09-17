package com.github.alvarohsp.repositorios;

import com.github.alvarohsp.entidades.Usuario;
import com.github.alvarohsp.entidades.UsuarioRetorno;
import com.github.alvarohsp.utilitarios.Criptografia;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProfessorRepositorio {

    public Response addAdmin(Usuario user) {

        Optional<Usuario> buscarUser = Usuario.find("email", user.getEmail())
                .singleResultOptional();

        if (buscarUser.isPresent()) {
            return Response.status(400).entity("Email já cadastrado!").build();
        }

        user.setSenha(Criptografia.criptografarSenha(user.getSenha()));
        user.setTipo(1);

        Usuario.persist(user);
        if (user.isPersistent()) {
            return Response.status(201).entity("Usuario Administrador: " + user.getNome() + " criado com sucesso.").build();
        }
        return Response.status(400).build();

    }

    public Response getAll(){
        List<Usuario> users = Usuario.list("SELECT m FROM Usuario m WHERE m.tipo = 1 ORDER BY id ASC");
        List<UsuarioRetorno> usersFront = new ArrayList<UsuarioRetorno>();
        for(Usuario u : users){
            usersFront.add(new UsuarioRetorno(u.id, u.getNome(), u.getEmail(), u.getIdade(), u.getFormacao(), u.getTipo()));
        }
        return Response.ok(usersFront).build();
    }

    public Response getAdminById(long id){
        Optional<Usuario> buscaUser = Usuario.find("id", id)
                .singleResultOptional();

        if (buscaUser.isEmpty() || buscaUser.get().getTipo() != 1) {
            return Response.status(404).entity("Não existe usuario Administrador com o id: " + id).build();
        }


        UsuarioRetorno userFront = new UsuarioRetorno(
                buscaUser.get().id,
                buscaUser.get().getNome(),
                buscaUser.get().getEmail(),
                buscaUser.get().getIdade(),
                buscaUser.get().getFormacao(),
                buscaUser.get().getTipo());

        return Response.ok(userFront).build();
    }
}
