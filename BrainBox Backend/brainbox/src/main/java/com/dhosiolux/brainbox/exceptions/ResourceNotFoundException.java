package com.dhosiolux.brainbox.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String errorMessage){
        super(errorMessage);
    }

    public ResourceNotFoundException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
