package com.epam.hotel.dao;

import com.epam.hotel.dao.api.ApplicationDtoDao;
import com.epam.hotel.entity.dto.ApplicationDto;
import com.epam.hotel.exception.DaoException;
import com.epam.hotel.dao.mapper.RowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class ApplicationDtoDaoImpl extends AbstractDao<ApplicationDto> implements ApplicationDtoDao {
    private static final String TOTAL_COUNT = "SELECT COUNT(*) FROM application JOIN room ON application.room_id = room.id " +
            "where application.status = 'CONFIRMED'";
    private static final String SELECT_PAGE_BY_CONDITION = "SELECT room.number,application.id, application.check_in,application.check_out,room.type,room.capacity, application.invoice " +
            "FROM application JOIN room ON application.room_id = room.id where application.status = 'CONFIRMED' LIMIT ? OFFSET ?";
    private static final String TOTAL_COUNT_BY_USER_ID = "SELECT COUNT(*) FROM application JOIN room ON application.room_id = room.id " +
            "where application.user_id = ?";
    private static final String GET_PAGE_BY_USER_ID = "SELECT room.number, application.id, application.check_in,application.check_out,room.type,application.capacity, application.invoice " +
            "FROM application JOIN room ON application.room_id = room.id where application.user_id = ? LIMIT ? OFFSET ?";

    protected ApplicationDtoDaoImpl(Connection connection, RowMapper<ApplicationDto> rowMapper, String table) {
        super(connection, rowMapper);
    }

    @Override
    public Long findRowCount() throws DaoException {
        Optional<Long> records = selectCountRecords(TOTAL_COUNT);
        return records.orElse(0L);
    }

    @Override
    public List<ApplicationDto> findPaginatePage(int items, int begin) throws DaoException {
        return executeQuery(SELECT_PAGE_BY_CONDITION, items, begin);
    }

    @Override
    public Long findRowCountByUserId(long id) throws DaoException {
        Optional<Long> records = selectCountRecords(TOTAL_COUNT_BY_USER_ID, id);
        return records.orElse(0L);
    }

    @Override
    public List<ApplicationDto> findPaginatePageByUserId(long id, int items, int begin) throws DaoException {
        return executeQuery(GET_PAGE_BY_USER_ID, id, items, begin);
    }

}
