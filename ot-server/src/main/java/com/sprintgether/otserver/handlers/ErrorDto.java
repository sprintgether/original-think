package com.sprintgether.otserver.handlers;

import com.sprintgether.otserver.exception.EnumErrorCode;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestion d'exception globale :  c'est un écouteur ou un listener qui va écouter sur toute l'application et à chaque fois qu'on lève une exception
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class ErrorDto {
    private Integer httpCode; // Pour le code http -- par exemple 404. donc pour entity not found le code sera par exemple not found http, ou 404
    private EnumErrorCode enumErrorCode; // pour nos code d'EnumErrorCode -- ENUM
    private String message;
    private List<String> listErrors = new ArrayList<>(); // liste d'érreurs renvoyée par les validateurs
}
