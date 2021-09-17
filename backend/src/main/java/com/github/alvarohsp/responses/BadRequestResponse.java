package com.github.alvarohsp.responses;

public class BadRequestResponse extends ResponseEntity{

    public BadRequestResponse() {
        super(400, "Bad Request");
    }

    public BadRequestResponse(String message) {
        super(400, message);
    }
}
