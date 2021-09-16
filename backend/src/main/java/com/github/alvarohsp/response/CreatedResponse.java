package com.github.alvarohsp.response;

import javax.enterprise.context.ApplicationScoped;

public class CreatedResponse extends ResponseEntity {

    public CreatedResponse() {
        super(201, "Created");
    }

    public CreatedResponse(String message) {
        super(201, message);
    }
}
