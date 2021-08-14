package com.sprintgether.otserver.exception;

public enum EnumErrorCode {

    ERROR_DB_ITEM_NOTFOUND("OTERRDB00001"),
    ERROR_DB_ITEM_ALREADY_EXIST("OTERRDB00002"),
    ERROR_DB_ITEM_INVALID("OTERRDB00003");

    private String code;

    EnumErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }


}
