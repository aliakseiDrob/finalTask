package com.epam.hotel.logic.validators.api;

public interface UserValidator {
    boolean isValid(String login, String password);
}
