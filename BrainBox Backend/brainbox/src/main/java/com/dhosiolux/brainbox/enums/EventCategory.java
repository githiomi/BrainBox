package com.dhosiolux.brainbox.enums;

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
    PEOPLE_AND_NETWORKING("Networking"),
    SCIENCE_AND_TECHNOLOGY("Science and Technology"),
    CAMPUS_EVENT("Campus Event"),
    HEALTH_AND_WELLNESS("Health and Wellness"),
    PERSONAL_DEVELOPMENT("Personal Development");

    private String categoryName;
    EventCategory(String categoryName) {
        this.categoryName = categoryName;
    }
}
