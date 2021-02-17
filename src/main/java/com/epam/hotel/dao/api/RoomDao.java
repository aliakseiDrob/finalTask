package com.epam.hotel.dao.api;

import com.epam.hotel.entity.Room;
import com.epam.hotel.exception.DaoException;

import java.time.LocalDate;
import java.util.List;

/**
 * Extending PersistenceDao interface for managing Room entities
 */

public interface RoomDao extends PersistentDao<Room> {
    /**
     * Finds all  na available rooms limited by time period
     *
     * @param check_in  start date for search
     * @param check_out end date to search
     * @return list of on available rooms
     * @throws DaoException in case of errors
     */
    List<Room> findAllAvailableRooms(LocalDate check_in, LocalDate check_out) throws DaoException;

}
