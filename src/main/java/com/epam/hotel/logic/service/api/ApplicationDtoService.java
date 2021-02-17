package com.epam.hotel.logic.service.api;

import com.epam.hotel.entity.dto.ApplicationDto;
import com.epam.hotel.exception.ServicesException;

import java.util.List;

/**
 * Interface for serving applicationDto (DTO) objects according to the business logics of application
 */
public interface ApplicationDtoService {
    /**
     * Returns number of pages depending on the number of entities on the page
     *
     * @param itemsPerPage number of entities on the page
     * @return number of pages
     * @throws ServicesException in case of errors
     */
    long findAmountPages(int itemsPerPage) throws ServicesException;

    /**
     * Returns list of ApplicationDto starting from begin position in the table
     *
     * @param itemsPerPage number of records from the table
     * @param page         starting position for search in the table
     * @return List of objects
     * @throws ServicesException in case of errors
     */

    List<ApplicationDto> findPaginatePageInvoices(int itemsPerPage, int page) throws ServicesException;

    /**
     * Returns number of pages depending on the number of entities on the page and user id
     *
     * @param id           user id
     * @param itemsPerPage number of entities on the page
     * @return number of pages
     * @throws ServicesException in case of errors
     */
    long findAmountPagesByUserId(long id, int itemsPerPage) throws ServicesException;

    /**
     * Returns list of ApplicationDto starting from begin position in the table by user id
     *
     * @param itemsPerPage number of records from the table
     * @param page         starting position for search in the table
     * @return List of ApplicationDto
     * @throws ServicesException in case of errors
     */
    List<ApplicationDto> findPaginatePageByUserId(Long id, int itemsPerPage, int page) throws ServicesException;
}
