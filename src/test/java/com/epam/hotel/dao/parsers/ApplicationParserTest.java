package com.epam.hotel.dao.parsers;


import com.epam.hotel.entity.Application;
import com.epam.hotel.entity.enums.ApplicationStatus;
import com.epam.hotel.entity.enums.RoomType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class ApplicationParserTest {
    private static final String DATE_CHECK_IN = "2021-02-12";
    private static final String DATE_CHECK_OUT = "2021-02-24";
    private final ApplicationParser applicationParser = new ApplicationParser();

    @Test
    public void testParseShouldReturnCorrectMapParameters() throws ParseException {
        LocalDate checkIn = LocalDate.parse(DATE_CHECK_IN);
        LocalDate checkOut = LocalDate.parse(DATE_CHECK_OUT);
        Application application = new Application(1L, checkIn,checkOut, RoomType.COMFORT, Byte.parseByte("2"), ApplicationStatus.IN_PROGRESS, 2L, 0L, new BigDecimal("0"));
        Map<String, Object> expectedMap = createExpectedMap();
        Map<String, Object> actualMap = applicationParser.parse(application);
        Assert.assertEquals(expectedMap, actualMap);
    }


    private Map<String, Object> createExpectedMap() throws ParseException {
        LocalDate checkIn = LocalDate.parse(DATE_CHECK_IN);
        LocalDate checkOut = LocalDate.parse(DATE_CHECK_OUT);
        Map<String, Object> fieldsMap = new LinkedHashMap<>();
        fieldsMap.put("id", 1L);
        fieldsMap.put("check_in", checkIn);
        fieldsMap.put("check_out", checkOut);
        fieldsMap.put("room_type", RoomType.COMFORT.toString());
        fieldsMap.put("capacity", Byte.valueOf("2"));
        fieldsMap.put("status", ApplicationStatus.IN_PROGRESS.toString());
        fieldsMap.put("user_id", 2L);
        fieldsMap.put("room_id", 0L);
        fieldsMap.put("invoice", new BigDecimal("0"));
        return fieldsMap;
    }
}