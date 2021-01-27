package com.epam.hotel.dao.mapper;

import com.epam.hotel.entity.dto.ApplicationDto;
import com.epam.hotel.entity.enums.RoomType;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ApplicationDtoRowMapper implements RowMapper<ApplicationDto> {
    private static final String CHECK_IN_DATE = "check_in";
    private static final String CHECK_OUT_DATE = "check_out";
    private static final String ROOM_TYPE = "type";
    private static final String CAPACITY = "capacity";
    private static final String ROOM_NUMBER = "number";
    private static final String INVOICE = "invoice";

    @Override
    public ApplicationDto map(ResultSet resultSet) throws SQLException {
        LocalDate checkIn = resultSet.getObject(CHECK_IN_DATE,LocalDate.class);
        LocalDate checkOut = resultSet.getObject(CHECK_OUT_DATE,LocalDate.class);
        String roomTypeValue = resultSet.getString(ROOM_TYPE);
        RoomType roomType = RoomType.valueOf(roomTypeValue);
        byte capacity = resultSet.getByte(CAPACITY);
        int roomNumber = resultSet.getInt(ROOM_NUMBER);
        BigDecimal invoice = resultSet.getBigDecimal(INVOICE);
        return new ApplicationDto(checkIn, checkOut, roomType, capacity, invoice, roomNumber);
    }
}
