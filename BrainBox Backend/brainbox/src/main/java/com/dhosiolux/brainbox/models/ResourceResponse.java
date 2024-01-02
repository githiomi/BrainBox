package com.dhosiolux.brainbox.models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

public record ResourceResponse<T> (
        String message,
        T resource,
        LocalDateTime timestamp
){}
