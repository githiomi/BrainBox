package com.dhosiolux.brainbox.exceptions;

import com.dhosiolux.brainbox.models.User;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String message){
        super(message);
    }

    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}