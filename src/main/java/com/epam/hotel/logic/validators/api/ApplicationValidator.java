package com.epam.hotel.logic.validators.api;

public interface ApplicationValidator {

    boolean isValidDateCheckIn(String dateCheckIn);

    boolean isValidDateCheckOut(String dateCheckIn,String dateCheckOut);

    boolean isValidRoomType(String type);

    boolean isValidCapacity(String capacity);
}
