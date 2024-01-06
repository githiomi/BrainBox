package com.dhosiolux.brainbox.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
public class ApplicationException {

    String errorMessage;
    Throwable throwableError;
    HttpStatus httpStatus;
    LocalDateTime timestamp;

    public ApplicationException (String errorMessage, Throwable throwableError, HttpStatus httpStatus){
        this.errorMessage = errorMessage;
        this.throwableError = throwableError;
        this.timestamp = LocalDateTime.now();
    }
}
