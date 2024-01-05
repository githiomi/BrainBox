package com.dhosiolux.brainbox.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    UUID eventId;
    String eventName;

    public Event(String eventName){
        this.eventId = UUID.randomUUID();
        this.eventName = eventName;
    }
}
