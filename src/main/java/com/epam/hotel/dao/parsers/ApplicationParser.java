package com.epam.hotel.dao.parsers;

import com.epam.hotel.entity.Application;
import com.epam.hotel.entity.enums.ApplicationStatus;
import com.epam.hotel.entity.enums.RoomType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;


public class ApplicationParser implements Parser<Application> {
    private static final String ID = "id";
    private static final String CHECK_IN_DATE = "check_in";
    private static final String CHECK_OUT_DATE = "check_out";
    private static final String ROOM_TYPE = "room_type";
    private static final String CAPACITY = "capacity";
    private static final String STATUS = "status";
    private static final String USER_ID = "user_id";
    private static final String ROOM_ID = "room_id";
    private static final String INVOICE = "invoice";

    @Override
    public Map<String, Object> parse(Application item) {
        Long id = item.getId();
        LocalDate check_in = item.getDateCheckIn();
        LocalDate check_out = item.getDateCheckOut();
        RoomType applicationRoomType = item.getType();
        String roomType = applicationRoomType.toString();
        byte capacity = item.getCapacity();
        ApplicationStatus applicationStatus = item.getStatus();
        String status = applicationStatus.toString();
        Long userId = item.getUserId();
        Long roomId = item.getRoomId();
        BigDecimal invoice = item.getInvoice();

        Map<String, Object> fieldsMap = new LinkedHashMap<>();
        fieldsMap.put(ID, id);
        fieldsMap.put(CHECK_IN_DATE, check_in);
        fieldsMap.put(CHECK_OUT_DATE, check_out);
        fieldsMap.put(ROOM_TYPE, roomType);
        fieldsMap.put(CAPACITY, capacity);
        fieldsMap.put(STATUS, status);
        fieldsMap.put(USER_ID, userId);
        fieldsMap.put(ROOM_ID, roomId);
        fieldsMap.put(INVOICE, invoice);

        return fieldsMap;
    }
}
