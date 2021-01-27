package com.epam.hotel.dao.parsers;


import com.epam.hotel.entity.Room;
import com.epam.hotel.entity.enums.RoomStatus;
import com.epam.hotel.entity.enums.RoomType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

public class RoomParserTest {
    private static final String ROOM_ID = "id";
    private static final String ROOM_TYPE = "type";
    private static final String ROOM_PRICE = "price";
    private static final String ROOM_PRICE_VALUE = "100";
    private static final String ROOM_STATUS = "status";
    private static final String ROOM_CAPACITY = "capacity";
    private static final String ROOM_NUMBER = "number";
    private static final int ROOM_NUMBER_VALUE = 27;
    private static final long ROOM_ID_VALUE = 1L;
    private static final int ROOM_CAPACITY_VALUE = 4;
    private final RoomParser roomParser = new RoomParser();

    @Test
    public void testParseShouldReturnCorrectMapParameters() throws ParseException {
        Room room = new Room(1L, RoomType.COMFORT, new BigDecimal(ROOM_PRICE_VALUE), RoomStatus.AVAILABLE, (byte) ROOM_CAPACITY_VALUE, ROOM_NUMBER_VALUE);
        Map<String, Object> expectedMap = createExpectedMap();
        Map<String, Object> actualMap = roomParser.parse(room);
        Assert.assertEquals(expectedMap, actualMap);

    }

    private Map<String, Object> createExpectedMap() throws ParseException {
        Map<String, Object> fieldsMap = new LinkedHashMap<>();
        fieldsMap.put(ROOM_ID, ROOM_ID_VALUE);
        RoomType roomType = RoomType.COMFORT;
        String type = roomType.toString();
        fieldsMap.put(ROOM_TYPE, type);
        fieldsMap.put(ROOM_PRICE, new BigDecimal(ROOM_PRICE_VALUE));
        RoomStatus roomStatus = RoomStatus.AVAILABLE;
        String status= roomStatus.toString();
        fieldsMap.put(ROOM_STATUS, status);
        fieldsMap.put(ROOM_CAPACITY, (byte) ROOM_CAPACITY_VALUE);
        fieldsMap.put(ROOM_NUMBER, ROOM_NUMBER_VALUE);
        return fieldsMap;
    }
}

