package com.github.alvarohsp.login;

import com.github.alvarohsp.response.UnauthorizedResponse;
import com.github.alvarohsp.utils.JsonConfigFile;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.annotation.Priority;
import javax.crypto.SecretKey;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Provider
@AuthorizeADM
@Priority(Priorities.AUTHENTICATION)
public class AuthorizeADMFilter implements ContainerRequestFilter {

    private final SecretKey CHAVE = Keys.hmacShaKeyFor(
            JsonConfigFile.getSecretKey().getBytes(StandardCharsets.UTF_8));

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        try{
            String token = authorizationHeader.substring("Bearer".length()).trim();

            var eoq = Jwts.parserBuilder()
                    .setSigningKey(CHAVE)
                    .build()
                    .parseClaimsJws(token);

            if(!eoq.getBody().get("admin").toString().equals("true")) {
                requestContext.abortWith(Response.status(401).entity(new UnauthorizedResponse()).build());
            }

        } catch (Exception e) {
            requestContext.abortWith(Response.status(401).entity(new UnauthorizedResponse()).build());
        }


    }
}
