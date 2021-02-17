package com.epam.hotel.dao;

import com.epam.hotel.connection.ConnectionPool;
import com.epam.hotel.connection.ProxyConnection;
import com.epam.hotel.dao.api.*;
import com.epam.hotel.exception.DaoException;
import com.epam.hotel.dao.mapper.*;
import com.epam.hotel.dao.parsers.*;


import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {
    private final ProxyConnection connection;

    public DaoHelper(ConnectionPool pool) throws DaoException {
        connection = pool.getConnection();
    }

    public UserDao createUserDao() {
        return new UserDaoImpl(connection, new UserRowMapper(), new UserParser(), "user", new QueryCreator("user"));
    }

    public UserDtoDao createUserDtoDao() {
        return new UserDtoDaoImpl(connection, new UserDtoRowMapper(), "user");
    }

    public ApplicationDao createApplicationDao() {
        return new ApplicationDaoImpl(connection, new ApplicationRowMapper(), new ApplicationParser(), "application", new QueryCreator("application"));
    }

    public RoomDao createRoomDao() {
        return new RoomDaoImpl(connection, new RoomRowMapper(), new RoomParser(), "room", new QueryCreator("room"));
    }


    public ApplicationDtoDao createApplicationDtoDao() {
        return new ApplicationDtoDaoImpl(connection, new ApplicationDtoRowMapper(), "application");
    }

    public void close() throws SQLException {
        connection.close();
    }

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void commitTransaction() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException eRollBack) {
                throw new DaoException(eRollBack);
            }
        }
    }
}
