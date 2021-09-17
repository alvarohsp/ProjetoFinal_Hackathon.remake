package com.github.alvarohsp.recursos;

import com.github.alvarohsp.entidades.Usuario;
import com.github.alvarohsp.auth.Authorize;
import com.github.alvarohsp.repositorios.AlunoRepositorio;
import com.github.alvarohsp.utilitarios.JsonConfigFile;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.nio.charset.StandardCharsets;

@Path("/aluno")
@RequestScoped
public class AlunoRecurso {

    private final SecretKey CHAVE = Keys.hmacShaKeyFor(
            JsonConfigFile.getSecretKey().getBytes(StandardCharsets.UTF_8));

    @Inject
    AlunoRepositorio repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        return repository.getAll();
    }

    @GET
    @Path("permit-all")
    @Authorize
    @Produces(MediaType.APPLICATION_JSON)
    public String hello(@Context HttpHeaders ctx){
        String token = ctx.getHeaderString(HttpHeaders.AUTHORIZATION).substring("Bearer".length()).trim();
        var tokenPronto = Jwts.parserBuilder()
                .setSigningKey(CHAVE)
                .build()
                .parseClaimsJws(token);

        return tokenPronto.getBody().get("userId").toString();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") long id){
        return repository.getUserById(id);

    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(Usuario user){
        return repository.addUser(user);
    }
}
