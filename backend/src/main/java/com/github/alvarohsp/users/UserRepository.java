package com.github.alvarohsp.users;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.github.alvarohsp.response.BadRequestResponse;
import com.github.alvarohsp.response.CreatedResponse;
import com.github.alvarohsp.response.NotFoundResponse;
import com.github.alvarohsp.response.OkResponse;
import com.github.alvarohsp.utils.*;

@ApplicationScoped
public class UserRepository {

    public Response getAll(){
        List<User> users = User.list("SELECT m FROM User m WHERE m.isAdmin = false ORDER BY id ASC");
        List<UserReturn> usersFront = new ArrayList<UserReturn>();
        for(User u : users){
            usersFront.add(new UserReturn(u.id,u.getName(),u.getTel(),u.getEmail()));
        }

        return Response.ok().entity(new OkResponse<List<UserReturn>>(usersFront)).build();
    }

    public Response getUserById(long id){
        Optional<User> buscarUser = User.find("id", id)
                .singleResultOptional();

        if (buscarUser.isEmpty() || buscarUser.get().getAdmin()) {
            return Response.status(404).entity(new NotFoundResponse("Não existe usuario com esse id")).build();
        }

        UserReturn userFront = new UserReturn();
        userFront.setId(buscarUser.get().id);
        userFront.setName(buscarUser.get().getName());
        userFront.setTel(buscarUser.get().getTel());
        userFront.setEmail(buscarUser.get().getEmail());

        return Response.ok(new OkResponse<UserReturn>(userFront)).build();
    }

    public Response addUser(User user) {

        Optional<User> verificarUser = User.find("email", user.getEmail())
                .singleResultOptional();

        if (verificarUser.isPresent()) {
//            return Response.status(400).entity("Usuario já existe").build();
            return Response.status(400).entity(new BadRequestResponse("Usuario já existe")).build();
        }

        user.setPassword(Crypto.criptografarSenha(user.getPassword()));
        user.setAdmin(false);


        User.persist(user);
        if (user.isPersistent()) {
//            return Response.status(201).entity("Usuario: " + user.getName() + " criado com sucesso.").build();
            return Response.status(201).entity(new CreatedResponse("Usuario criado com sucesso.")).build();
        }
//        return Response.status(400).build();
        return Response.status(400).entity(new BadRequestResponse()).build();

    }
}
