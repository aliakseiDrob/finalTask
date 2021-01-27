package com.epam.hotel.dao.api;

import com.epam.hotel.entity.Identifier;
import com.epam.hotel.exception.DaoException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PersistentDao <T extends Identifier> extends Dao<T> {

    void save(T item) throws SQLException;
    void removeById(Long id) throws SQLException;
    Optional<T> findById(Long id) throws DaoException;
}
