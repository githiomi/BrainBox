package com.dhosiolux.brainbox.enums;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
public enum EventCategory {

    ART("Art"),
    FOOD("Food"),
    MUSIC("Music"),
    SPORTS("Sports"),
    SCIENCE("Science"),
    FASHION_AND_DESIGN("Fashion and Design"),
    BUSINESS_AND_FINANCE("Business and Finance"),
    BOOKS_AND_LITERATURE("Books and Literature"),
    PEOPLE_AND_NETWORKING("People and Networking"),
    SCIENCE_AND_TECHNOLOGY("Science and Technology"),
    CAMPUS_EVENT("Campus Event"),
    HEALTH_AND_WELLNESS("Health and Wellness"),
    PERSONAL_DEVELOPMENT("Personal Development");

    private final String categoryName;

    EventCategory(String categoryName) {
        this.categoryName = categoryName;
    }
}
