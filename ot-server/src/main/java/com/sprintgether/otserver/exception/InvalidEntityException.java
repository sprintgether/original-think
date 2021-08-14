package com.sprintgether.otserver.exception;

import lombok.Getter;

import java.util.List;

/**
 * cet exeception est levé si on essaye d'enregistrer(ou de mettre à jour) une entité dans la base de données
 */
public class InvalidEntityException extends RuntimeException{
    @Getter
    private EnumErrorCode enumErrorCode;

    @Getter
    private List<String> listErrors; // ces listes d'érreurs seront fournis par la validation

    public InvalidEntityException(String message){
        super(message);
    }

    public InvalidEntityException(String message, Throwable cause){
        super(message, cause);
    }

    public InvalidEntityException(String message, Throwable cause, EnumErrorCode enumErrorCode){
        super(message, cause);
        this.enumErrorCode = enumErrorCode;
    }

    public InvalidEntityException(String message, EnumErrorCode enumErrorCode){
        super(message);
        this.enumErrorCode = enumErrorCode;
    }

    public InvalidEntityException(String message, EnumErrorCode enumErrorCode, List<String> listErrors){
        super(message);
        this.enumErrorCode = enumErrorCode;
        this.listErrors = listErrors;
    }
}
