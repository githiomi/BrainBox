package com.dhosiolux.brainbox.exceptions;

public class RequestException extends RuntimeException {

    public RequestException(String errorMessage){
        super(errorMessage);
    }

    public RequestException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
