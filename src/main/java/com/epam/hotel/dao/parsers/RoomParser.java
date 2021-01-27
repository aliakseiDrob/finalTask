package com.epam.hotel.dao.parsers;

import com.epam.hotel.entity.Room;
import com.epam.hotel.entity.enums.RoomStatus;
import com.epam.hotel.entity.enums.RoomType;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class RoomParser implements Parser<Room> {
    private static final String ID = "id";
    private static final String TYPE = "type";
    private static final String PRICE = "price";
    private static final String STATUS = "status";
    private static final String CAPACITY = "capacity";
    private static final String NUMBER = "number";

    @Override
    public Map<String, Object> parse(Room item) {
        Long id = item.getId();
        RoomType roomType = item.getType();
        String type = roomType.toString();
        BigDecimal price = item.getPrice();
        RoomStatus roomStatus = item.getStatus();
        String status = roomStatus.toString();
        byte capacity = item.getCapacity();
        int number = item.getNumber();
        Map<String, Object> fieldsMap = new LinkedHashMap<>();
        fieldsMap.put(ID, id);
        fieldsMap.put(TYPE, type);
        fieldsMap.put(PRICE, price);
        fieldsMap.put(STATUS, status);
        fieldsMap.put(CAPACITY, capacity);
        fieldsMap.put(NUMBER, number);
        return fieldsMap;
    }
}
