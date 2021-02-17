package com.epam.hotel.dao.api;

import com.epam.hotel.entity.dto.ApplicationDto;
import com.epam.hotel.exception.DaoException;

import java.util.List;

/**
 * Extending Dao interface for managing ApplicationDto entities
 */
public interface ApplicationDtoDao extends Dao<ApplicationDto> {
    /**
     * Finds the number of invoices  by user id in the table
     *
     * @param id user id
     * @return the number of invoices by user id
     * @throws DaoException in case of errors
     */
    Long findRowCountByUserId(long id) throws DaoException;

    /**
     * Finds range of records invoices by user id
     *
     * @param id    user id
     * @param begin start position for query
     * @param items number of records
     * @return list of invoices by user id
     * @throws DaoException in case of errors
     */
    List<ApplicationDto> findPaginatePageByUserId(long id, int items, int begin) throws DaoException;
}
