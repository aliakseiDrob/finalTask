package com.epam.hotel.logic.service.api;

import com.epam.hotel.entity.Application;
import com.epam.hotel.exception.DaoException;
import com.epam.hotel.exception.ServicesException;

import java.util.List;

/**
 * Interface for serving Application objects according to the business logics of Application
 */
public interface ApplicationService {
    /**
     * Returns Application by id
     *
     * @param id Application id
     * @return Application object
     * @throws ServicesException when business logics errors occur
     */
    Application findById(Long id) throws ServicesException;

    /**
     * Save Application object
     *
     * @param application Application object
     * @throws ServicesException when business logics errors occur
     */
    void saveApplication(Application application) throws ServicesException;

    /**
     * Returns difference between two dates in Application.
     *
     * @param id Application id
     * @return difference between two dates in Application
     * @throws ServicesException in case of errors
     */
    int getDifferenceDate(Long id) throws ServicesException;

    /**
     * Returns number of pages depending on the number of Applications on the page
     *
     * @param itemsPerPage number of Applications on the page
     * @return number of pages
     * @throws ServicesException in case of errors
     */
    long findAmountPages(int itemsPerPage) throws ServicesException;

    /**
     * Returns list of Applications starting from begin position in the table
     *
     * @param itemsPerPage number of records from the table
     * @param page         starting position for search in the table
     * @return List of Applications
     * @throws ServicesException in case of errors
     */
    List<Application> findPageApplications(int itemsPerPage, int page) throws ServicesException;

    /**
     * Returns list of Applications in progress starting from begin position in the table
     *
     * @param itemsPerPage number of records from the table
     * @param page         starting position for search in the table
     * @return List of Applications
     * @throws ServicesException in case of errors
     */
    List<Application> findPageApplicationsInProgress(int itemsPerPage, int page) throws ServicesException;

    /**
     * Returns number of pages depending on the number of Applications in progress on the page
     *
     * @param itemsPerPage number of Applications in progress on the page
     * @return number of pages
     * @throws ServicesException in case of errors
     */
    long findAmountPagesInProgress(int itemsPerPage) throws ServicesException;

    /**
     * Returns number of pages depending on the number of Applications in progress and user id on the page
     *
     * @param itemsPerPage number of Applications in progress on the page
     * @param id           User id
     * @return number of pages
     * @throws ServicesException in case of errors
     */
    long findAmountPagesByUserId(long id, int itemsPerPage) throws ServicesException;

    /**
     * Returns list of Applications by User id starting from begin position in the table
     *
     * @param itemsPerPage number of records from the table
     * @param page         starting position for search in the table
     * @param id           User id
     * @return List of Applications
     * @throws ServicesException in case of errors
     */
    List<Application> findPageByUserId(long id, int itemsPerPage, int page) throws ServicesException;
}
