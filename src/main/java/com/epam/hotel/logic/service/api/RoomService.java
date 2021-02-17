package com.epam.hotel.logic.service.api;

import com.epam.hotel.entity.Room;
import com.epam.hotel.exception.DaoException;
import com.epam.hotel.exception.ServicesException;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface for serving Room objects according to the business logics of Room
 */
public interface RoomService {
    /**
     * Save Room object
     *
     * @param room Room object
     * @throws ServicesException when business logics errors occur
     */
    void addRoom(Room room) throws ServicesException;

    /**
     * changes the status of room (available/unavailable)
     *
     * @param id Room id
     * @throws ServicesException in case of errors
     */
    void blockUnblockRoom(Long id) throws ServicesException;

    /**
     * saves an edited room and changes the status of all applications by this room id
     *
     * @param room Room Object
     * @throws ServicesException in case of errors
     */
    void editRoom(Room room) throws ServicesException;

    /**
     * Returns list of an available Rooms limited by time period
     *
     * @param check_in  start date to search
     * @param check_out end date to search
     * @return List of Applications
     * @throws ServicesException in case of errors
     */
    List<Room> getAllAvailableRooms(LocalDate check_in, LocalDate check_out) throws ServicesException;

    /**
     * Returns Room by id
     *
     * @param id Room id
     * @return Room object
     * @throws ServicesException when business logics errors occur
     */
    Room findById(Long id) throws ServicesException;

    /**
     * Returns number of pages depending on the number of Rooms on the page
     *
     * @param itemsPerPage number of Rooms on the page
     * @return number of pages
     * @throws ServicesException in case of errors
     */
    long findAmountPages(int itemsPerPage) throws ServicesException;

    /**
     * Returns list of Rooms starting from begin position in the table
     *
     * @param itemsPerPage number of records from the table
     * @param page         starting position for search in the table
     * @return List of Rooms
     * @throws ServicesException in case of errors
     */
    List<Room> findPageRooms(int itemsPerPage, int page) throws ServicesException;
}
