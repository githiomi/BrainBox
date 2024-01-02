package com.dhosiolux.brainbox.models;

import java.util.UUID;

public record Event(
        UUID eventId,
        String eventName
) {

    public Event(String eventName){
        this(UUID.randomUUID(), eventName);
    }
}
