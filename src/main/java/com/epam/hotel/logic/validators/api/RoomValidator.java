package com.epam.hotel.logic.validators.api;

/**
 * Interface for validation data received from the browser
 */
public interface RoomValidator {
    /**
     * This method checks if the Room type is valid
     *
     * @param type Room type
     * @return boolean value. If Room type valid returns true otherwise returns false
     */
    boolean isValidRoomType(String type);

    /**
     * This method checks if the price value is valid
     *
     * @param price Room price
     * @return boolean value. If capacity valid returns true otherwise returns false
     */
    boolean isValidPrice(String price);

    /**
     * This method checks if the number of the Room is valid
     *
     * @param number Room number
     * @return boolean value. If number valid returns true otherwise returns false
     */
    boolean isValidRoomNumber(String number);

    /**
     * This method checks if the capacity value is valid
     *
     * @param capacity number of guests in the Room
     * @return boolean value. If capacity valid returns true otherwise returns false
     */
    boolean isValidCapacity(String capacity);
}
