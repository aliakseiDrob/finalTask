package com.epam.hotel.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This interface describes classes that should extract fields from ResultSet
 * into entity object
 *
 * @param <T> any class that implements Entity interface
 */
public interface RowMapper<T> {
    /**
     * Performs actions to extract fields from ResultSet and create entity class object
     * with extracted data
     *
     * @param resultSet data received from data base (as table)
     * @return entity class object built by this method
     * @throws SQLException occurs in case of errors
     */
    T map(ResultSet resultSet) throws SQLException;

}
