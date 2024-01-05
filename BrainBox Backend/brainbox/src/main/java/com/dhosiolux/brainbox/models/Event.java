package com.dhosiolux.brainbox.models;

import com.dhosiolux.brainbox.enums.EventCategory;
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
    EventCategory eventCategory;

    public Event(String eventName, EventCategory eventCategory){
        this.eventId = UUID.randomUUID();
        this.eventName = eventName;
        this.eventCategory = eventCategory;
    }
}
