package com.sprintgether.otserver.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
@Data
public class PasswordResetException extends RuntimeException {
    private String token;
    private String message;

    public PasswordResetException(String token, String message){
        super(String.format("Couldn't reset password for [%s]: [%s]", token, message));
        this.token = token;
        this.message = message;
    }
}
