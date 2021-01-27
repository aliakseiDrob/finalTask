package com.epam.hotel.entity.enums;

public enum ApplicationStatus {
    IN_PROGRESS("in progress"),
    CONFIRMED("confirmed"),
    REJECTED("rejected");

    ApplicationStatus(String state) {
        this.state = state;
    }

    private final String state;

    public String getState() {
        return state;
    }
}
