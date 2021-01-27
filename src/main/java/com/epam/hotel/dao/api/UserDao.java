package com.epam.hotel.dao.api;

import com.epam.hotel.entity.User;
import com.epam.hotel.exception.DaoException;

import java.util.Optional;

public interface UserDao extends PersistentDao<User> {
        Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;
}
