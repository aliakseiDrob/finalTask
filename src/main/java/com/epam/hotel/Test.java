package com.epam.hotel;

import com.epam.hotel.entity.Application;
import com.epam.hotel.entity.enums.ApplicationStatus;
import com.epam.hotel.entity.enums.RoomType;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Test {
    int a;
    int b;

    public String execute() {
        if (isValid(a, b)) {
            return "oneString";
        }
        return "anotherString";
    }

    private boolean isValid(int a, int b) {
        if (a < b) {
            return true;
        } else return false;
    }
}
