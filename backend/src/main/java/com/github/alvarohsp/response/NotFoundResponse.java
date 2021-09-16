package com.github.alvarohsp.response;

import javax.enterprise.context.ApplicationScoped;

public class NotFoundResponse extends ResponseEntity{

    public NotFoundResponse() {
        super(404, "Não encontrado!");
    }

    public NotFoundResponse(String message) {
        super(404, message);
    }
}
