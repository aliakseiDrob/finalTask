package com.epam.hotel.dao;

import com.epam.hotel.dao.api.PersistentDao;
import com.epam.hotel.entity.Identifier;
import com.epam.hotel.exception.DaoException;
import com.epam.hotel.dao.mapper.RowMapper;
import com.epam.hotel.dao.parsers.Parser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractPersistentDao<T extends Identifier> extends AbstractDao<T> implements PersistentDao<T> {
    private static final String WHERE_ID = " WHERE id = ?";
    private static final String DELETE_FROM = "DELETE FROM ";
    private static final String GET_ALL = "SELECT * FROM ";
    private final Parser<T> parser;
    private final QueryCreator queryCreator;

    public AbstractPersistentDao(Connection connection, RowMapper<T> rowMapper, Parser<T> parser, String tableName, QueryCreator queryCreator) {
        super(connection, rowMapper, tableName);
        this.parser = parser;
        this.queryCreator = queryCreator;
    }

    @Override
    public void save(T item) throws SQLException {
        Long itemId = item.getId();
        Map<String, Object> fieldMap = parser.parse(item);
        String query;
        List<Object> fieldsList = null;
        Object[] fieldsArray;
        if (itemId == 0) {
            query = queryCreator.createSaveQuery(fieldMap);
            fieldsList = new ArrayList<>(fieldMap.values());
        } else {
            query = queryCreator.createUpdateQuery(fieldMap);
            fieldsList = new ArrayList<>(fieldMap.values());
            fieldsList.remove(0);
            fieldsList.add(itemId);
        }
        fieldsArray = fieldsList.toArray();
        PreparedStatement preparedStatement = createStatement(query, fieldsArray);
        preparedStatement.executeUpdate();
    }

    @Override
    public void removeById(Long id) throws SQLException {
        PreparedStatement preparedStatement = createStatement(DELETE_FROM + getTableName() + WHERE_ID, id);
        preparedStatement.executeUpdate();

    }

    @Override
    public Optional<T> findById(Long id) throws DaoException {
        return executeForSingleResult(GET_ALL + super.getTableName() + WHERE_ID, id);
    }
}
