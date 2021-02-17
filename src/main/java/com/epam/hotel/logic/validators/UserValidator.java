package com.epam.hotel.logic.validators;

public class UserValidator implements com.epam.hotel.logic.validators.api.UserValidator {
    @Override
    public boolean isValid(String login, String password) {
        if ((login == null) || (password == null)) {
            return false;
        }
        boolean loginContains = login.matches("[A-Za-z0-9_]+");
        boolean loginLength = (login.length() > 4) && (login.length() < 50);
        boolean passwordContains = password.matches("[A-Za-z0-9_]+");
        boolean passwordLength = (password.length() > 4) && (login.length() < 50);
        return loginContains && loginLength && passwordContains && passwordLength;
    }
}
