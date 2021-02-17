package com.epam.hotel.dao;

import com.epam.hotel.dao.api.UserDao;
import com.epam.hotel.entity.User;
import com.epam.hotel.exception.DaoException;
import com.epam.hotel.dao.mapper.RowMapper;
import com.epam.hotel.dao.parsers.Parser;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractPersistentDao<User> implements UserDao {
    private static final String SELECT_PAGE = "SELECT * FROM user LIMIT ? OFFSET ?";
    private static final String TOTAL_COUNT_RECORDS = "SELECT COUNT(*) FROM user";
    private static final String FIND_USER_BY_LOGIN_AND_ID = "SELECT * FROM user WHERE login = ? and password = SHA1(?)";

    public UserDaoImpl(Connection connection, RowMapper<User> rowMapper, Parser<User> parser, String table, QueryCreator queryCreator) {
        super(connection, rowMapper, parser, table, queryCreator);

    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(FIND_USER_BY_LOGIN_AND_ID, login, password);
    }

    @Override
    public List<User> findPaginatePage(int items, int begin) throws DaoException {
        return executeQuery(SELECT_PAGE, items, begin);
    }

    @Override
    public Long findRowCount() throws DaoException {
        Optional<Long> records = selectCountRecords(TOTAL_COUNT_RECORDS);
        return records.orElse(0L);
    }
}