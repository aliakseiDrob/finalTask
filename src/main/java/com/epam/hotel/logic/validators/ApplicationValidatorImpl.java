package com.epam.hotel.logic.validators;

import com.epam.hotel.entity.enums.RoomType;
import com.epam.hotel.logic.validators.api.ApplicationValidator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static java.time.temporal.ChronoUnit.DAYS;

public class ApplicationValidatorImpl implements ApplicationValidator {
    private final static LocalDate TODAY = LocalDate.now();              // TODO not static
    private static final int MAX_CAPACITY = 4;
    private static final int MIN_CAPACITY = 1;
    private static final int ONE_YEAR_IN_DAYS = 366;
    private static final String EMPTY_STRING = "";
    private static final int NULL_INT_VALUE = 0;


    @Override
    public boolean isValidDateCheckIn(String dateCheckIn) {
        LocalDate checkInDate;
        if (dateCheckIn == null || EMPTY_STRING.equals(dateCheckIn.trim())) {
            return false;
        }
        try {
            checkInDate = LocalDate.parse(dateCheckIn);             // TODO
        } catch (DateTimeParseException e) {
            return false;
        }
        long daysBetween = DAYS.between(TODAY, checkInDate);
        return (daysBetween > NULL_INT_VALUE && daysBetween < ONE_YEAR_IN_DAYS);
    }

    @Override
    public boolean isValidDateCheckOut(String dateCheckIn, String dateCheckOut) {
        LocalDate checkIn;
        LocalDate checkOut;
        if (dateCheckOut == null || EMPTY_STRING.equals(dateCheckOut.trim())) {
            return false;
        }
        try {
            checkIn = LocalDate.parse(dateCheckIn);
            checkOut = LocalDate.parse(dateCheckOut);
        } catch (DateTimeParseException e) {
            return false;
        }
        long daysBetween = DAYS.between(checkIn, checkOut);
        return (daysBetween > NULL_INT_VALUE && daysBetween < ONE_YEAR_IN_DAYS);
    }

    @Override
    public boolean isValidRoomType(String type) {
        if (type == null || EMPTY_STRING.equals(type.trim())) {
            return false;
        }
        return type.equals(RoomType.STANDARD.toString()) || type.equals(RoomType.COMFORT.toString())
                || type.equals(RoomType.LUX.toString()) || type.equals(RoomType.PRESIDENT.toString());
    }

    @Override
    public boolean isValidCapacity(String capacity) {
        int guests;
        try {
            guests = Integer.parseInt(capacity);
        } catch (NumberFormatException e) {
            return false;
        }
        return (guests >= MIN_CAPACITY) && (guests <= MAX_CAPACITY);
    }
}
