package com.epam.hotel.logic.validators;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserValidatorTest {
    private final UserValidator userValidator = new UserValidator();
    private static final String INVALID_LOGIN = "+asd/";
    private static final String INVALID_PASSWORD = "44*-sd/";
    private static final String SHORT_LOGIN = "asd";
    private static final String SHORT_PASSWORD = "44";
    private static final String EMPTY_LOGIN = "";
    private static final String EMPTY_PASSWORD = "";
    private static final String VALID_LOGIN = "login";
    private static final String VALID_PASSWORD = "password";

    @DataProvider(name = "incorrectValues")
    public static Object[][] testIncorrectValues() {
        return new Object[][]{
                {INVALID_LOGIN, INVALID_PASSWORD},
                {SHORT_LOGIN, SHORT_PASSWORD},
                {INVALID_LOGIN, VALID_PASSWORD},
                {VALID_LOGIN, SHORT_PASSWORD},
                {EMPTY_LOGIN, EMPTY_PASSWORD}
        };
    }

    @Test(dataProvider = "incorrectValues")
    public void testIsValidShouldReturnFalseWhenDataIncorrect(String login, String password) {
        boolean isValid = userValidator.isValid(login, password);
        Assert.assertFalse(isValid);
    }

    @Test
    public void testIsValidShouldReturnTrueWhenDataCorrect() {
        boolean isValid = userValidator.isValid(VALID_LOGIN, VALID_PASSWORD);
        Assert.assertTrue(isValid);
    }
}
