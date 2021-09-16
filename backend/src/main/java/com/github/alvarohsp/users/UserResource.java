package com.github.alvarohsp.users;

import com.github.alvarohsp.login.Authorize;
import com.github.alvarohsp.utils.JsonConfigFile;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.nio.charset.StandardCharsets;

@Path("/users")
@RequestScoped
public class UserResource {

    private final SecretKey CHAVE = Keys.hmacShaKeyFor(
            JsonConfigFile.getSecretKey().getBytes(StandardCharsets.UTF_8));

    @Inject
    UserRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Authorize
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
    public Response addUser(User user){
        return repository.addUser(user);
    }
}
