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
    Integer httpStatusCode;
    LocalDateTime timestamp;

    public ExceptionPayload(String errorMessage, Integer httpStatusCode){
        this.errorMessage = errorMessage;
        this.httpStatusCode = httpStatusCode;
        this.timestamp = LocalDateTime.now();
    }
}
