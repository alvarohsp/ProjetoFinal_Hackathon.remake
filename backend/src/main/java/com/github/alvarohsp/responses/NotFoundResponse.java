package com.github.alvarohsp.responses;

public class NotFoundResponse extends ResponseEntity{

    public NotFoundResponse() {
        super(404, "Não encontrado!");
    }

    public NotFoundResponse(String message) {
        super(404, message);
    }
}
