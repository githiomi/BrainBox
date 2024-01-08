package com.dhosiolux.brainbox.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResourceNotFoundException extends RuntimeException {

    HttpStatus httpStatus;

    public ResourceNotFoundException(String errorMessage){
        super(errorMessage);
    }

    public ResourceNotFoundException(String errorMessage, HttpStatus httpStatus){
        super(errorMessage);
        this.httpStatus = httpStatus;
    }

    public ResourceNotFoundException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
