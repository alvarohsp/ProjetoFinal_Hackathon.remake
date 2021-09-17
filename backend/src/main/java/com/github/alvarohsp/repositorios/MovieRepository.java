package com.github.alvarohsp.repositorios;

import com.github.alvarohsp.entidades.Movie;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@ApplicationScoped
public class MovieRepository {

    public Response getAll(String sort) {
        if (!(sort.equals("title") || sort.equals("id") || sort.equals("country") || sort
                .equals("director") || sort.equals("description"))) {
            sort = "id";
        }
        List<Movie> movies = Movie.list("SELECT m FROM Movie m ORDER BY " + sort + " ASC");
        return Response.ok(movies).build();
    }

    public Response getById(Long id){
        return Movie.findByIdOptional(id)
                .map(movie -> Response.ok(movie).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    public Response getByCountry(String country) {
        List<Movie> movies = Movie.list("SELECT m FROM Movie m WHERE m.country = ?1 ORDER BY id " +
                "DESC", country);
        return  Response.ok(movies).build();
    }

    public Response getByTitle(String title) {
//        return Movie.find("title", title)
//                .singleResultOptional()
//                .map(movie -> Response.ok(movie).build())
//                .orElse(Response.status(Response.Status.NOT_FOUND).build());

        List<Movie> movies = Movie.list("SELECT m FROM Movie m WHERE LOWER(m.title) LIKE LOWER(CONCAT('%', ?1, '%'))", title);
        if (movies.toArray().length == 0) {
            return Response.status(404).entity("Nenhum filme foi encontrado").build();
        }
        return Response.ok(movies).build();
    }

    public Response create(Movie movie) {
        Movie.persist(movie);
        if(movie.isPersistent()) {
            return Response.created(URI.create("/movies" + movie.id)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    public Response update(Long id, Movie movie) {

            Movie filme = Movie.findById(id);

            if(filme == null) {
                return Response.status(404).entity("Filme não encontrado").build();
            }
            filme.setCountry(movie.getCountry());
            filme.setDescription(movie.getDescription());
            filme.setTitle(movie.getTitle());
            filme.setDirector(movie.getDirector());

//            Movie.persist(filme);
            return Response.ok().entity("Filme editado com sucesso!").build();


    }

    public Response deleteById(Long id) {
        boolean deleted = Movie.deleteById(id);
        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(400).build();
    }

}
