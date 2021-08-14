package com.sprintgether.otserver.exception;

public class OtCommonException extends Exception {
    EnumErrorCode errorCode;
    Object data;

    public OtCommonException(EnumErrorCode errorCode,String message,Object data, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.data = data;
    }
    public OtCommonException(EnumErrorCode errorCode, Object data,Throwable cause) {
        super( cause);
        this.errorCode = errorCode;
        this.data = data;
    }
    public OtCommonException(EnumErrorCode errorCode,Object data) {
        this.errorCode = errorCode;
        this.data = data;
    }

    public OtCommonException(EnumErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    public EnumErrorCode getErrorCode() {
        return errorCode;
    }

}
