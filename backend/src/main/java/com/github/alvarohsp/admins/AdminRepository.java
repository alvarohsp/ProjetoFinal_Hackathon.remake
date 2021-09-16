package com.github.alvarohsp.admins;

import com.github.alvarohsp.users.User;
import com.github.alvarohsp.users.UserReturn;
import com.github.alvarohsp.utils.Crypto;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AdminRepository {

    public Response addAdmin(User user) {

        Optional<User> buscarUser = User.find("email", user.getEmail())
                .singleResultOptional();

        if (buscarUser.isPresent()) {
            return Response.status(400).entity("Email já cadastrado!").build();
        }

        user.setPassword(Crypto.criptografarSenha(user.getPassword()));
        user.setAdmin(true);

        User.persist(user);
        if (user.isPersistent()) {
            return Response.status(201).entity("Usuario Administrador: " + user.getName() + " criado com sucesso.").build();
        }
        return Response.status(400).build();

    }

    public Response getAll(){
        List<User> users = User.list("SELECT m FROM User m WHERE m.isAdmin = true ORDER BY id ASC");
        List<UserReturn> usersFront = new ArrayList<UserReturn>();
        for(User u : users){
            usersFront.add(new UserReturn(u.id,u.getName(),u.getTel(),u.getEmail()));
        }
        return Response.ok(usersFront).build();
    }

    public Response getAdminById(long id){
        Optional<User> buscaUser = User.find("id", id)
                .singleResultOptional();

        if (buscaUser.isEmpty() || !buscaUser.get().getAdmin()) {
            return Response.status(404).entity("Não existe usuario Administrador com o id: " + id).build();
        }


        UserReturn userFront = new UserReturn(
                buscaUser.get().id,
                buscaUser.get().getName(),
                buscaUser.get().getTel(),
                buscaUser.get().getEmail());

        return Response.ok(userFront).build();
    }
}
