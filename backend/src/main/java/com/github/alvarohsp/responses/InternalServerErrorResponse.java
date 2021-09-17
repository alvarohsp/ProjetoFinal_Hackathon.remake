package com.github.alvarohsp.responses;

public class InternalServerErrorResponse extends ResponseEntity {

    public InternalServerErrorResponse() {
        super(500, "Internal Server Error");
    }

    public InternalServerErrorResponse(String message) {
        super(500, message);
    }
}
