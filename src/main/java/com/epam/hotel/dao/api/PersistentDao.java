package com.epam.hotel.dao.api;

import com.epam.hotel.entity.Identifier;
import com.epam.hotel.exception.DaoException;

import java.util.Optional;

public interface PersistentDao<T extends Identifier> extends Dao<T> {
    /**
     * This method is used for saving and updating Entity objects in the data source
     *
     * @param item Entity object to be saved
     * @throws DaoException in case of errors
     */
    void save(T item) throws DaoException;

    /**
     * This method is used for deleting Entity objects in the data source
     *
     * @param id Object id to be deleted
     * @throws DaoException in case of errors
     */
    void removeById(Long id) throws DaoException;

    /**
     * This method finds Entity object in the data source by id
     *
     * @param id Object id to be deleted
     * @throws DaoException in case of errors
     */
    Optional<T> findById(Long id) throws DaoException;
}
