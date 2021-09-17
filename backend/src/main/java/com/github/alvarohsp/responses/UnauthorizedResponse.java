package com.github.alvarohsp.responses;

public class UnauthorizedResponse extends ResponseEntity {

    public UnauthorizedResponse() {
        super(401, "Acesso Negado!");
    }

    public UnauthorizedResponse(String message){
        super(401, message);
    }
}
