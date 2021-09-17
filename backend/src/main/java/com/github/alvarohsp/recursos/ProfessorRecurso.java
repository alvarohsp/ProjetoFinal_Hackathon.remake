package com.github.alvarohsp.recursos;

import com.github.alvarohsp.repositorios.ProfessorRepositorio;
import com.github.alvarohsp.entidades.Usuario;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/professor")
public class ProfessorRecurso {

    @Inject
    ProfessorRepositorio repository;

    @GET
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
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(Usuario user){
        return repository.addAdmin(user);
    }
}
