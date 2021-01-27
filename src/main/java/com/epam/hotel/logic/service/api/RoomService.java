package com.epam.hotel.logic.service.api;

import com.epam.hotel.entity.Room;
import com.epam.hotel.exception.ServicesException;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {

    void addRoom(Room room) throws ServicesException;

    void blockUnblockRoom(Long id) throws ServicesException;

    void editRoom(Room room) throws ServicesException;

    List<Room> getAllAvailableRooms(LocalDate check_in, LocalDate check_out) throws ServicesException;

    Room findById(Long id) throws ServicesException;

    long findAmountPages(int itemsPerPage) throws ServicesException;

    List<Room> findPageRooms(int itemsPerPage, int page) throws ServicesException;
}
