package com.epam.hotel.dao.mapper;

import com.epam.hotel.entity.Room;
import com.epam.hotel.entity.enums.RoomStatus;
import com.epam.hotel.entity.enums.RoomType;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomRowMapper implements RowMapper<Room> {
    private static final String ID = "id";
    private static final String TYPE = "type";
    private static final String PRICE = "price";
    private static final String STATUS = "status";
    private static final String CAPACITY = "capacity";
    private static final String NUMBER = "number";

    @Override
    public Room map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(ID);
        String type = resultSet.getString(TYPE);
        RoomType roomType = RoomType.valueOf(type);
        BigDecimal price = resultSet.getBigDecimal(PRICE);
        String status = resultSet.getString(STATUS);
        RoomStatus roomStatus = RoomStatus.valueOf(status);
        byte capacity = resultSet.getByte(CAPACITY);
        int number = resultSet.getInt(NUMBER);
        return new Room(id, roomType, price, roomStatus, capacity, number);
    }
}
