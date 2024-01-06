package com.dhosiolux.brainbox.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(value = {RequestException.class})
    public ResponseEntity<Object> eventNotFoundException(RequestException ex){

        // Exception payload
        ApplicationException exception = new ApplicationException(
                "The event was not found in the database",
                ex,
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );

        return ResponseEntity.status(exception.httpStatus).body(exception);
    }

}
