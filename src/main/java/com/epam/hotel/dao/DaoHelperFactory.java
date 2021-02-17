package com.epam.hotel.dao;

import com.epam.hotel.connection.ConnectionPool;
import com.epam.hotel.exception.DaoException;

public class DaoHelperFactory {
    public DaoHelper create() throws DaoException {

        return new DaoHelper(ConnectionPool.getInstance());
    }
}
