package com.github.alvarohsp.admins;

import com.github.alvarohsp.login.Authorize;
import com.github.alvarohsp.login.AuthorizeADM;
import com.github.alvarohsp.users.User;
import com.github.alvarohsp.users.UserRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users-admin")
public class AdminResource {

    @Inject
    AdminRepository repository;

    @GET
    @Authorize
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        return repository.getAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") long id){
        return repository.getAdminById(id);

    }

    @POST
    @AuthorizeADM
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user){
        return repository.addAdmin(user);
    }
}
