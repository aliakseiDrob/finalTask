package com.epam.hotel.dao.api;
import com.epam.hotel.entity.Room;
import com.epam.hotel.exception.DaoException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface RoomDao extends PersistentDao<Room> {
    List<Room> findAllAvailableRooms(LocalDate check_in,LocalDate check_out) throws DaoException;

}
