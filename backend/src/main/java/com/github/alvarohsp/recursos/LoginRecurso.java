package com.github.alvarohsp.recursos;

import com.github.alvarohsp.entidades.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import com.github.alvarohsp.utilitarios.*;

@Path("/login")
public class LoginRecurso {

    private final SecretKey CHAVE = Keys.hmacShaKeyFor(
            JsonConfigFile.getSecretKey().getBytes(StandardCharsets.UTF_8));

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(Usuario user){
        try {
            Optional<Usuario> verificaUser = Usuario.find("email", user.getEmail())
                    .singleResultOptional();

            if (verificaUser.isEmpty()) {
                return Response.status(404).entity("Usuario não existe").build();
            }

            if (!Criptografia.verificarSenha(user.getSenha(), verificaUser.get().getSenha())){
                return Response.status(401).entity("Senha incorreta").build();
            }

            String jwtToken = Jwts.builder()
                    .setSubject(verificaUser.get().getNome())
                    .setIssuer("localhost:8080")
                    .claim("type", verificaUser.get().getTipo())
                    .claim("userId", verificaUser.get().id)
                    .setIssuedAt(new Date())
                    .setExpiration(Date.from(
                            LocalDateTime.now().plusMinutes(15L)
                            .atZone(ZoneId.systemDefault())
                            .toInstant()))
                    .signWith(CHAVE)
                    .compact();

            return Response.status(200).entity(jwtToken).build();

        } catch (Exception ex) {
            return Response.status(500).entity(ex.getMessage()).build();

        }
    }
}
