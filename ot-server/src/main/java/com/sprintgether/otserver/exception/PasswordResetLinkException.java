package com.sprintgether.otserver.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
@Data
public class PasswordResetLinkException extends RuntimeException{
    private String user;
    private String message;

    public PasswordResetLinkException(String user, String message) {
        super(String.format("Failed to send password reset link to User: '%s'", message));
        //super(String.format("Failed to send password reset link to User [%d] : '%s'", user, message));

        this.user = user;
        this.message = message;
    }

}
