package com.github.alvarohsp.recursos;

import com.github.alvarohsp.entidades.Movie;
import com.github.alvarohsp.repositorios.MovieRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/movies")
public class MovieResource {

    @Inject
    MovieRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@DefaultValue("id") @QueryParam("sort") String sort) {
        return repository.getAll(sort);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id){
        return repository.getById(id);
    }

    @GET
    @Path("country/{country}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByCountry(@PathParam("country") String country) {
        return repository.getByCountry(country);
    }

    @GET
    @Path("title/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByTitle(@PathParam("title") String title) {
        return repository.getByTitle(title);
    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Movie movie) {
        return repository.create(movie);
    }

    @PUT
    @Path("{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, Movie movie) {
        return repository.update(id, movie);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") Long id) {
        return repository.deleteById(id);
    }
















}
