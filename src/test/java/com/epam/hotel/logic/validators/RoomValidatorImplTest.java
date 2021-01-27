package com.epam.hotel.logic.validators;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RoomValidatorImplTest {
    private final static RoomValidatorImpl roomValidator = new RoomValidatorImpl();

    @DataProvider(name = "incorrectRoomPrice")
    public static Object[][] testIncorrectRoomPrice() {
        return new Object[][]{
                {"-1"},
                {"0.222"},
                {""},
                {"word"}
        };
    }
    @DataProvider(name = "incorrectRoomNumber")
    public static Object[][] testIncorrectRoomNumber() {
        return new Object[][]{
                {"-1"},
                {"0.2"},
                {""},
                {"word"},
                {"10000"}
        };
    }
    @DataProvider(name = "incorrectRoomCapacity")
    public static Object[][] testIncorrectCapacity() {
        return new Object[][]{
                {"-1"},
                {"0.2"},
                {""},
                {"word"},
                {"6"}
        };
    }


    @Test
    public void testIsValidRoomTypeShouldReturnTrueWhenTypeCorrect() {
        String validRoomType = "STANDARD";
        boolean isValid = roomValidator.isValidRoomType(validRoomType);
        Assert.assertTrue(isValid);
    }

    @Test
    public void testIsValidRoomTypeShouldReturnFalseWhenTypeInCorrect() {
        String invalidRoomType = "Type";
        boolean isValid = roomValidator.isValidRoomType(invalidRoomType);
        Assert.assertFalse(isValid);
    }

    @Test(dataProvider ="incorrectRoomPrice")
    public void testIsValidPriceShouldReturnFalseWhenPriceInCorrect(String price) {
        boolean isValid = roomValidator.isValidPrice(price);
        Assert.assertFalse(isValid);
    }

    @Test
    public void testIsValidPriceShouldReturnTrueWhenPriceCorrect() {
        String invalidPrice = "66.22";
        boolean isValid = roomValidator.isValidPrice(invalidPrice);
        Assert.assertTrue(isValid);
    }

    @Test(dataProvider ="incorrectRoomNumber")
    public void testIsValidRoomNumberShouldReturnFalseNumberIncorrect(String number) {
        boolean isValid = roomValidator.isValidRoomNumber(number);
        Assert.assertFalse(isValid);
    }

    @Test
    public void testIsValidRoomNumberShouldReturnTrueWhenNumberCorrect() {
        String validRoomNumber = "22";
        boolean isValid = roomValidator.isValidRoomNumber(validRoomNumber);
        Assert.assertTrue(isValid);
    }

    @Test(dataProvider ="incorrectRoomCapacity")
    public void testIsValidCapacityShouldReturnFalseWhenCapacityInCorrect(String capacity) {
        boolean isValid = roomValidator.isValidCapacity(capacity);
        Assert.assertFalse(isValid);
    }

    @Test
    public void testIsValidCapacityShouldReturnTrueCapacityCorrect() {
        String validCapacity = "4";
        boolean isValid = roomValidator.isValidCapacity(validCapacity);
        Assert.assertTrue(isValid);
    }
}
