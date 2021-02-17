package com.epam.hotel.dao.api;

import com.epam.hotel.entity.User;
import com.epam.hotel.exception.DaoException;

import java.util.Optional;

/**
 * Extending PersistenceDao interface for managing User entities
 */

public interface UserDao extends PersistentDao<User> {
    /**
     * Method for authenticating user by login and password
     *
     * @param login    user login
     * @param password user password
     * @return optional of user
     * @throws DaoException in case of errors
     */
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;
}
