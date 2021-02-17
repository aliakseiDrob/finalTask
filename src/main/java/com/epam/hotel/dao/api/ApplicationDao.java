package com.epam.hotel.dao.api;

import com.epam.hotel.entity.Application;
import com.epam.hotel.exception.DaoException;

import java.time.LocalDate;
import java.util.List;

/**
 * Extending PersistenceDao interface for managing Application entities
 */
public interface ApplicationDao extends PersistentDao<Application> {
    /**
     * Returns difference between two dates in Application.
     *
     * @param id Application id
     * @return difference between two dates in Application
     * @throws DaoException in case of errors
     */
    int differenceBetweenDate(Long id) throws DaoException;

    /**
     * Finds the number of applications in progress in the table
     *
     * @return the number of applications in progress
     * @throws DaoException in case of errors
     */
    Long findRowCountInProgress() throws DaoException;

    /**
     * Finds range of records Applications in progress
     *
     * @param begin start position for query
     * @param items number of rows
     * @return the list of applications in progress
     * @throws DaoException in case of errors
     */
    List<Application> findPaginatePageInProgress(int items, int begin) throws DaoException;

    /**
     * Finds the number of applications in progress by user id in the table
     *
     * @param id user id
     * @return the number of applications in progress by user id
     * @throws DaoException in case of errors
     */
    Long findRowCountByUserId(Long id) throws DaoException;

    /**
     * Finds range of records Applications in progress by user id
     *
     * @param id    user id
     * @param begin start position for query
     * @param items number of rows
     * @return list of applications in progress by user id
     * @throws DaoException in case of errors
     */
    List<Application> findPaginatePageByUserId(long id, int items, int begin) throws DaoException;

    /**
     * Finds all  records Applications by room id
     *
     * @param id    room id
     * @param begin start date to search
     * @param end   end date for search
     * @return list of applications by room id and limited by time period
     * @throws DaoException in case of errors
     */

    List<Application> findAllByRoomIdAndTimePeriod(Long id, LocalDate begin, LocalDate end) throws DaoException;

    /**
     * Finds all  records Applications by user id and limited by date
     *
     * @param id    room id
     * @param begin start date to search
     * @return list of applications by user id and limited by date
     * @throws DaoException in case of errors
     */

    List<Application> findAllByUserIdAndTimePeriod(Long id, LocalDate begin) throws DaoException;

}
