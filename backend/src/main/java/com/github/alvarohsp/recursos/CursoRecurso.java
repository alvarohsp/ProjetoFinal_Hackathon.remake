package com.github.alvarohsp.recursos;

import com.github.alvarohsp.entidades.Curso;
import com.github.alvarohsp.entidades.Usuario;
import com.github.alvarohsp.repositorios.CursoRepositorio;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/curso")
public class CursoRecurso {

    @Inject
    CursoRepositorio repositorio;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        return repositorio.getAll();
    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCurso(Curso curso){
        return repositorio.addCurso(curso);
    }
}

