package com.github.alvarohsp.response;

import javax.enterprise.context.ApplicationScoped;

public class InternalServerErrorResponse extends ResponseEntity {

    public InternalServerErrorResponse() {
        super(500, "Internal Server Error");
    }

    public InternalServerErrorResponse(String message) {
        super(500, message);
    }
}
