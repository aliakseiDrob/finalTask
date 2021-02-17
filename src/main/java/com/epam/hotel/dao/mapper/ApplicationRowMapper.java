package com.epam.hotel.dao.mapper;

import com.epam.hotel.entity.*;
import com.epam.hotel.entity.enums.ApplicationStatus;
import com.epam.hotel.entity.enums.RoomType;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ApplicationRowMapper implements RowMapper<Application> {
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
    public Application map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(ID);
        LocalDate checkIn = resultSet.getObject(CHECK_IN_DATE, LocalDate.class);
        LocalDate checkOut = resultSet.getObject(CHECK_OUT_DATE, LocalDate.class);
        String roomTypeValue = resultSet.getString(ROOM_TYPE);
        RoomType roomType = RoomType.valueOf(roomTypeValue);
        byte capacity = resultSet.getByte(CAPACITY);
        String statusValue = resultSet.getString(STATUS);
        ApplicationStatus status = ApplicationStatus.valueOf(statusValue);
        Long userId = resultSet.getLong(USER_ID);
        Long roomId = resultSet.getLong(ROOM_ID);
        BigDecimal invoice = resultSet.getBigDecimal(INVOICE);
        return new Application(id, checkIn, checkOut, roomType, capacity, status, userId, roomId, invoice);
    }
}
