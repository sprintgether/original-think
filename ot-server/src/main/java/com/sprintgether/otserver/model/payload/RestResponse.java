package com.sprintgether.otserver.model.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sprintgether.otserver.model.enums.ResponseStatus;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponse {
    private Object data;
    private String message;
    private ResponseStatus status;
    private int code;

    public RestResponse() {
    }

    public RestResponse(String message, ResponseStatus status, int code) {
        this.message = message;
        this.status = status;
        this.code = code;
    }

    public RestResponse(Object data, String message, ResponseStatus status, int code) {
        this.data = data;
        this.message = message;
        this.status = status;
        this.code = code;
    }
}
