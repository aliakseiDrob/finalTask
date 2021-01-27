package com.epam.hotel.logic.validators.api;

public interface RoomValidator {

    boolean isValidRoomType(String type);

    boolean isValidPrice(String price);

    boolean isValidRoomNumber(String number);

    boolean isValidCapacity(String capacity);
}
