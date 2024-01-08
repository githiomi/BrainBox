package com.dhosiolux.brainbox.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionPayload {

    String errorMessage;
    public HttpStatus httpStatus;
    LocalDateTime timestamp;

    public ExceptionPayload(String errorMessage, HttpStatus httpStatus){
        this.errorMessage = errorMessage;
        this.timestamp = LocalDateTime.now();
    }
}
