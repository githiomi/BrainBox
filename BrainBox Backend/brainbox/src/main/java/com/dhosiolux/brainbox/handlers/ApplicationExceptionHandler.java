package com.dhosiolux.brainbox.handlers;

import com.dhosiolux.brainbox.exceptions.ExceptionPayload;
import com.dhosiolux.brainbox.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionPayload> eventNotFoundException(ResourceNotFoundException ex){

        // Exception payload
        ExceptionPayload exception = new ExceptionPayload(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );

        return ResponseEntity.status(exception.httpStatus).body(exception);
    }

}
