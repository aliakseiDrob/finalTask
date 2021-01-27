package com.epam.hotel.dao.api;

import com.epam.hotel.entity.Identifier;
import com.epam.hotel.exception.DaoException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao <T extends Identifier>{


    List<T> findPaginatePage(int items, int begin) throws DaoException;

    Long findRowCount() throws DaoException;
}
