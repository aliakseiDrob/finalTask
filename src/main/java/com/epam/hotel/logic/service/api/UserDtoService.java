package com.epam.hotel.logic.service.api;

import com.epam.hotel.entity.dto.UserDto;
import com.epam.hotel.exception.ServicesException;

import java.util.List;

/**
 * Interface for serving UserDto(Dto) objects according to the business logics of UserDto
 */
public interface UserDtoService {

    /**
     * Returns number of pages depending on the number of UsersDto on the page
     *
     * @param itemsPerPage number of UsersDto on the page
     * @return number of pages
     * @throws ServicesException in case of errors
     */
    long findAmountPages(int itemsPerPage) throws ServicesException;

    /**
     * Returns list of UsersDto starting from begin position in the table
     *
     * @param itemsPerPage number of records from the table
     * @param page         starting position for search in the table
     * @return List of UsersDto
     * @throws ServicesException in case of errors
     */
    List<UserDto> findPageUsers(int itemsPerPage, int page) throws ServicesException;
}
