package com.example.driverscars.controllers;

import com.example.driverscars.dto.response.ExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class ExceptionController {

    Logger logger = LoggerFactory.getLogger(ExceptionController.class); /* If there is no Lombok */

    @ExceptionHandler(value = {MethodArgumentNotValidException.class, SQLIntegrityConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        assert fieldError != null;
        String message = " in Object: "
                + fieldError.getObjectName()
                + ", field: "
                + fieldError.getField()
                + " -> "
                + fieldError.getDefaultMessage();
        logger.warn("Handling " + fieldError.getClass().getSimpleName() + message);
        return new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), "invalid input Data", message);
    }

}
