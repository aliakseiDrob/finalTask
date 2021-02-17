package com.epam.hotel.dao;

import com.epam.hotel.dao.api.UserDtoDao;
import com.epam.hotel.entity.dto.UserDto;
import com.epam.hotel.exception.DaoException;
import com.epam.hotel.dao.mapper.RowMapper;


import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UserDtoDaoImpl extends AbstractDao<UserDto> implements UserDtoDao {
    private static final String SELECT_PAGE = "SELECT * FROM user LIMIT ? OFFSET ?";
    private static final String TOTAL_COUNT_RECORDS = "SELECT COUNT(*) FROM user";

    public UserDtoDaoImpl(Connection connection, RowMapper<UserDto> rowMapper, String table) {
        super(connection, rowMapper);

    }


    @Override
    public List<UserDto> findPaginatePage(int items, int begin) throws DaoException {
        return executeQuery(SELECT_PAGE, items, begin);
    }

    @Override
    public Long findRowCount() throws DaoException {
        Optional<Long> scalar = selectCountRecords(TOTAL_COUNT_RECORDS);
        return scalar.orElse(0L);
    }
}
