package com.epam.hotel.logic.validators.api;

/**
 * Interface for validation data received from the browser
 */
public interface UserValidator {
    /**
     * This method checks if the login and password is valid
     *
     * @param login    User login
     * @param password User password
     * @return boolean value. If login and password valid returns true otherwise returns false
     */
    boolean isValid(String login, String password);
}
