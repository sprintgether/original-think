package com.sprintgether.otserver.handlers;

import com.sprintgether.otserver.exception.OtDBItemNotFoundException;
import com.sprintgether.otserver.model.enums.ResponseStatus;
import com.sprintgether.otserver.model.payload.ExceptionMessage;
import com.sprintgether.otserver.model.payload.RestResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(RestExceptionHandler.class);

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @ExceptionHandler(OtDBItemNotFoundException.class)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.NOT_FOUND)
    public RestResponse otDBItemNotFoundException(HttpServletRequest request, OtDBItemNotFoundException exception) {
        ExceptionMessage message = new ExceptionMessage();
        message.setDate(LocalDateTime.now().format(formatter));
        message.setPath(request.getRequestURI());
        message.setClassName(exception.getClass().getName());
        message.setMessage(exception.getMessage());
        LOGGER.error("Erreur {}, Entité {}, Champ {}, Valeur {} Introuvable",
                exception.getErrorCode().toString(), exception.getEntity(), exception.getField(), exception.getValue()
        );
        exception.printStackTrace();
        return new RestResponse(message, "Item not found", ResponseStatus.FAILED, 404);
    }

    @ExceptionHandler(ValidationException.class)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResponse handleTypeMismatchException(HttpServletRequest request, TypeMismatchException exception) {
        return new RestResponse("Validation Exception", ResponseStatus.FAILED, 400);
    }

    // TODO: handle other errors here

    @ExceptionHandler(Exception.class)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResponse genericExceptionHandler(HttpServletRequest request, Exception exception) {
        ExceptionMessage message = new ExceptionMessage();
        message.setDate(LocalDateTime.now().format(formatter));
        message.setPath(request.getRequestURI());
        message.setClassName(exception.getClass().getName());
        message.setMessage(exception.getLocalizedMessage());
        LOGGER.error(exception.getLocalizedMessage(), exception.getCause());
        exception.printStackTrace();
        return new RestResponse(message, "Internal server error", ResponseStatus.FAILED, 500);
    }
}