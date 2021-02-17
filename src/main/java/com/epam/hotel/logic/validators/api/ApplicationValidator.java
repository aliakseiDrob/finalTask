package com.epam.hotel.logic.validators.api;

/**
 * Interface for validation data received from the browser
 */
public interface ApplicationValidator {
    /**
     * This method checks if the date is valid
     *
     * @param dateCheckIn arrival date
     * @return boolean value. If date valid returns true otherwise returns false
     */
    boolean isValidDateCheckIn(String dateCheckIn);

    /**
     * This method checks if the date of departure is valid
     *
     * @param dateCheckOut arrival date
     * @return boolean value. If date valid returns true otherwise returns false
     */
    boolean isValidDateCheckOut(String dateCheckIn, String dateCheckOut);

    /**
     * This method checks if the Room type is valid
     *
     * @param type Room type
     * @return boolean value. If Room type valid returns true otherwise returns false
     */
    boolean isValidRoomType(String type);

    /**
     * This method checks if the capacity value is valid
     *
     * @param capacity number of guests in the Application
     * @return boolean value. If capacity valid returns true otherwise returns false
     */
    boolean isValidCapacity(String capacity);
}
