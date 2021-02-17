package com.epam.hotel.logic.service.api;

import com.epam.hotel.entity.User;
import com.epam.hotel.exception.ServicesException;

import java.util.Optional;

/**
 * Interface for serving User objects according to the business logics of User
 */
public interface UserService {
    /**
     * This method is used for user authentication
     *
     * @param login    user login
     * @param password user password
     * @return Optional of user if found
     * @throws ServicesException when business logics errors occur
     */
    Optional<User> login(final String login, final String password) throws ServicesException;

    /**
     * changes the status of User (active/blocked) and
     * changes the status of all applications limited by one months
     *
     * @param userId User id
     * @throws ServicesException in case of errors
     */
    void blockUnblockUser(Long userId) throws ServicesException;

    /**
     * changes the status  of User to blocked and
     * changes the status of all applications to rejected by user id
     *
     * @param userId User id
     * @throws ServicesException in case of errors
     */
    void deleteUser(Long userId) throws ServicesException;

}
