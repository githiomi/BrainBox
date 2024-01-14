package com.dhosiolux.brainbox.models;

import com.dhosiolux.brainbox.enums.EventCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    UUID eventId;
    String eventName;
    EventCategory eventCategory;
    String createdBy;
    LocalDateTime createdAt;
    LocalDateTime updatedOn;

    public Event(String eventName, EventCategory eventCategory, String createdBy){
        this.eventId = UUID.randomUUID();
        this.eventName = eventName;
        this.eventCategory = eventCategory;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
        this.updatedOn = null;
    }
}
