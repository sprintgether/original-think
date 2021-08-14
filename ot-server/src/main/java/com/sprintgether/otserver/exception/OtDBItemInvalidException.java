package com.sprintgether.otserver.exception;

public class OtDBItemInvalidException extends OtCommonException {
    String entity;
    String value;

    public OtDBItemInvalidException(EnumErrorCode errorCode, String message, Throwable cause, String entity, String field, String value) {
        super(errorCode, message, cause);
        this.entity = entity;
        this.value = value;
    }

    public OtDBItemInvalidException(EnumErrorCode errorCode, Throwable cause, String entity, String field, String value) {
        super(errorCode, cause);
        this.entity = entity;
        this.value = value;
    }

    public OtDBItemInvalidException(EnumErrorCode errorCode,String entity, String field, String value) {
        super(errorCode);
        this.entity = entity;
        this.value = value;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
