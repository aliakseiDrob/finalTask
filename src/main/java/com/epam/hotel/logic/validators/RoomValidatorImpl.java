package com.epam.hotel.logic.validators;

import com.epam.hotel.logic.validators.api.RoomValidator;

public class RoomValidatorImpl implements RoomValidator {
    private static final String PRICE_PATTERN = "[0-9]+[.]?+[0-9]{0,2}";

    @Override
    public boolean isValidRoomType(String type) {
        if (type.trim().equals("")) {
            return false;
        }
        return type.equals("STANDARD") || type.equals("COMFORT") || type.equals("LUX") || type.equals("PRESIDENT");
    }

    @Override
    public boolean isValidPrice(String price) {
        try {
            Double.parseDouble(price);
        } catch (NumberFormatException e) {
            return false;
        }
        return price.matches(PRICE_PATTERN);
    }

    @Override
    public boolean isValidRoomNumber(String number) {
        int roomNumber;
        try {
            roomNumber = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return false;
        }
        return (roomNumber > 0) && (roomNumber < 9999);
    }

    @Override
    public boolean isValidCapacity(String capacity) {
        int guests;
        try {
            guests = Integer.parseInt(capacity);
        } catch (NumberFormatException e) {
            return false;
        }
        return (guests > 0) && (guests < 5);
    }
}
