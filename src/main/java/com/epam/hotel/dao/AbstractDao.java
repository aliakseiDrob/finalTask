package com.epam.hotel.dao;

import com.epam.hotel.dao.api.Dao;
import com.epam.hotel.entity.Identifier;
import com.epam.hotel.exception.DaoException;
import com.epam.hotel.dao.mapper.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public abstract class AbstractDao<T extends Identifier> implements Dao<T> {
    private final Connection connection;
    private final String tableName;
    private final RowMapper<T> rowMapper;


    public AbstractDao(Connection connection, RowMapper<T> rowMapper, String tableName) {
        this.connection = connection;
        this.tableName = tableName;
        this.rowMapper = rowMapper;

    }

    protected List<T> executeQuery(String query, Object... params) throws DaoException {
        List<T> result = new ArrayList<>();
        try (PreparedStatement preparedStatement = createStatement(query, params);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                T resultItem = rowMapper.map(resultSet);
                result.add(resultItem);
            }
            return result;
        } catch (SQLException e) {                // TODO add message
            throw new DaoException(e);
        }
    }

    // package access,for method differenceBetweenDate() in ApplicationDaoImpl
    PreparedStatement createStatement(String query, Object... params) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (int i = 1; i <= params.length; i++) {
            preparedStatement.setObject(i, params[i - 1]);
        }
        return preparedStatement;
    }

    protected Optional<T> executeForSingleResult(String query, Object... params) throws DaoException {
        List<T> items = executeQuery(query, params);
        if (items.size() == 1) {
            return Optional.of(items.get(0));
        } else if (items.size() > 1) {
            throw new IllegalArgumentException("More than one record found");
        } else {
            return Optional.empty();
        }
    }

    public String getTableName() {
        return tableName;
    }


    protected Optional<Long> selectCountRecords(String query, Object... params) throws DaoException {
        try (PreparedStatement preparedStatement = createStatement(query, params);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                Long result = resultSet.getLong(1);
                return Optional.of(result);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
