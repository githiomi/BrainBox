package com.dhosiolux.brainbox.models;

import com.dhosiolux.brainbox.enums.EventCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "events_table")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID eventId;
    String eventName;
    EventCategory eventCategory;
    String createdBy;
    LocalDate createdAt;
    LocalDateTime updatedOn;

    public Event(String eventName, EventCategory eventCategory, String createdBy){
        this.eventId = UUID.randomUUID();
        this.eventName = eventName;
        this.eventCategory = eventCategory;
        this.createdBy = createdBy;
        this.createdAt = LocalDate.now();
        this.updatedOn = null;
    }

    public Event(String eventName, EventCategory eventCategory, String createdBy, LocalDate date) {
        this.eventId = UUID.randomUUID();
        this.eventName = eventName;
        this.eventCategory = eventCategory;
        this.createdBy = createdBy;
        this.createdAt = date;
        this.updatedOn = null;
    }
}
