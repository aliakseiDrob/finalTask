package com.epam.hotel.logic.validators;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
public class ApplicationValidatorImplTest {
    private final ApplicationValidatorImpl applicationValidator = new ApplicationValidatorImpl();
    private static final String INVALID_DATE = "2021-13-22";
    private static final String EMPTY_STING = "";
    private static final LocalDate today = LocalDate.now();
    //guest cannot fill in an application for a today
    private static final String TODAY = today.toString();
    // guest cannot fill in an application for a date later than 1 year
    private static final String MORE_THAN_YEAR = today.plusDays(369).toString();
    private static final String VALID_DATE = today.plusDays(1).toString();

    @DataProvider(name="invalidCheckInData")
    public static Object[][] testCheckInProvider() {
        return new Object[][]{
                {INVALID_DATE},
                {EMPTY_STING},
                {TODAY},
                {MORE_THAN_YEAR}
        };
    }

    @DataProvider(name="invalidCheckOutData")
    public static Object[][] testCheckOutProvider() {
        return new Object[][]{
                {VALID_DATE, INVALID_DATE},
                {VALID_DATE, EMPTY_STING},
                {VALID_DATE, TODAY},
                {VALID_DATE, MORE_THAN_YEAR}
        };
    }

    @Test(dataProvider = "invalidCheckInData")
    public void testIsValidDateCheckInShouldReturnFalseWhenDateIncorrect(String date) {
        boolean isValidDateCheckIn = applicationValidator.isValidDateCheckIn(date);
        Assert.assertFalse(isValidDateCheckIn);
    }

    @Test
    public void testValidDateCheckInShouldReturnTrueWhenDateValid() {
        boolean isValidDateCheckIn = applicationValidator.isValidDateCheckIn(VALID_DATE);
        Assert.assertTrue(isValidDateCheckIn);
    }

    @Test(dataProvider = "invalidCheckOutData")
    public void testIsValidDateCheckOutShouldReturnFalseWhenDateIncorrect(String checkIn, String checkOut) {
        boolean isValidDateCheckIn = applicationValidator.isValidDateCheckOut(checkIn, checkOut);
        Assert.assertFalse(isValidDateCheckIn);
    }

    @Test
    public void testIsValidDateCheckOutShouldReturnTrueWhenDateValid() {
        LocalDate validCheckOut = today.plusDays(2);
        String validCheckOutToString = validCheckOut.toString();
        boolean isValidDateCheckIn = applicationValidator.isValidDateCheckOut(VALID_DATE, validCheckOutToString);
        Assert.assertTrue(isValidDateCheckIn);
    }

    @Test
    public void testIsValidRoomTypeShouldReturnTrueWhenTypeCorrect() {
        String roomType = "STANDARD";
        boolean isValidRoomType = applicationValidator.isValidRoomType(roomType);
        Assert.assertTrue(isValidRoomType);
    }

    @Test
    public void testisValidRoomTypeShouldReturnFalseWhenTypeIncorrect() {
        String roomType = "INVALID";
        boolean isValidRoomType = applicationValidator.isValidRoomType(roomType);
        Assert.assertFalse(isValidRoomType);
    }

    @Test
    public void testisValidCapacityShouldReturnTrueWhenCapacityCorrect() {
        String validCapacity = "3";
        boolean isValidRoomType = applicationValidator.isValidCapacity(validCapacity);
        Assert.assertTrue(isValidRoomType);
    }

    @Test
    public void testisValidCapacityShouldReturnFalseWhenCapacityIncorrect() {
        String validCapacity = "-1";
        boolean isValidRoomType = applicationValidator.isValidCapacity(validCapacity);
        Assert.assertFalse(isValidRoomType);
    }
}
