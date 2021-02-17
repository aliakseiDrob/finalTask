package com.epam.hotel.dao.api;

import com.epam.hotel.entity.Identifier;
import com.epam.hotel.exception.DaoException;

import java.util.List;

/**
 * Common interface for all entity classes of the application for interacting with data sources
 *
 * @param <T> type of handling instances
 */

public interface Dao<T extends Identifier> {

    /**
     * Returns list of objects starting from begin position in the table
     *
     * @param items number of records from the table
     * @param begin starting position for search in the table
     * @return List of objects
     * @throws DaoException in case of errors
     */
    List<T> findPaginatePage(int items, int begin) throws DaoException;

    /**
     * Finds number of rows in the table
     *
     * @return number of records in the table
     * @throws DaoException in case of errors
     */

    Long findRowCount() throws DaoException;
}
