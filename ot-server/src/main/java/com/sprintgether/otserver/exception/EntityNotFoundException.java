package com.sprintgether.otserver.exception;

import lombok.Getter;


/**
 * c'est une exception générique qui sera levée si on cherche une entité sans le trouvé ou alors l'entité demandé n'existe pas
 */
public class EntityNotFoundException extends RuntimeException{

    @Getter
    private EnumErrorCode enumErrorCode;

    public EntityNotFoundException(String message){
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public EntityNotFoundException(String message, Throwable cause, EnumErrorCode enumErrorCode){
        super(message, cause);
        this.enumErrorCode = enumErrorCode;
    }

    public EntityNotFoundException(String message, EnumErrorCode enumErrorCode){
        super(message);
        this.enumErrorCode = enumErrorCode;
    }
}
