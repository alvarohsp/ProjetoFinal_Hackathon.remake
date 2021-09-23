package com.github.alvarohsp.responses;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OkResponse<T> extends ResponseEntity {

    private T data;

    public OkResponse() {
        super(200, "Ok");
        this.data = null;
    }

    public OkResponse(String message) {
        super(200, message);
        this.data = null;
    }

    public OkResponse(T data) {
        super(200, "Ok");
        this.data = data;

    }

    public OkResponse(String message, T data) {
        super(200, message);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
