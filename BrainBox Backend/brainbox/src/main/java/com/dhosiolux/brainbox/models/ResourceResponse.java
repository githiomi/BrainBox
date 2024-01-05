package com.dhosiolux.brainbox.models;

import java.time.LocalDateTime;

public record ResourceResponse<T> (
        String message,
        T resource,
        LocalDateTime timestamp
){}
