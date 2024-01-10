package com.dhosiolux.brainbox.handlers;

import com.dhosiolux.brainbox.exceptions.ExceptionPayload;
import com.dhosiolux.brainbox.exceptions.ResourceNotFoundException;
import com.dhosiolux.brainbox.exceptions.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionPayload> eventNotFoundException(ResourceNotFoundException ex){

        // Exception payload
        ExceptionPayload exception = new ExceptionPayload(
                ex.getMessage(),
                ex.getHttpStatus().value()
        );

        return ResponseEntity.status(ex.getHttpStatus()).body(exception);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ExceptionPayload> userAlreadyExistsException(UserAlreadyExistsException ex){
        ExceptionPayload payload = new ExceptionPayload(
                ex.getMessage(),
                BAD_REQUEST.value()
        );

        return ResponseEntity.status(BAD_REQUEST).body(payload);
    }

}
