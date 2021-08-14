package com.sprintgether.otserver.exception;

public class InvalidTokenRequestException extends RuntimeException {
    private String message;
    private String tokenValue;
    private String msg;

    public InvalidTokenRequestException(String message, String tokenValue, String msg){
        this.message = message;
        this.tokenValue = tokenValue;
        this.msg = msg;
    }

}
