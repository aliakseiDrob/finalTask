package com.epam.hotel.dao;

import com.epam.hotel.dao.api.ApplicationDao;
import com.epam.hotel.entity.Application;
import com.epam.hotel.exception.DaoException;
import com.epam.hotel.dao.mapper.RowMapper;
import com.epam.hotel.dao.parsers.Parser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ApplicationDaoImpl extends AbstractPersistentDao<Application> implements ApplicationDao {
    private static final String DIFFERENCE_DATE_QUERY = "SELECT DATEDIFF(check_out,check_in) AS difference from application where id =?";
    private static final String TOTAL_COUNT = "SELECT COUNT(*) FROM application";
    private static final String SELECT_PAGE = "SELECT * FROM application LIMIT ? OFFSET ?";
    private static final String TOTAL_COUNT_IN_PROGRESS = "SELECT COUNT(*) FROM application WHERE status = 'IN_PROGRESS'";
    private static final String SELECT_PAGE_IN_PROGRESS = "SELECT * FROM application WHERE status = 'IN_PROGRESS' LIMIT ? OFFSET ?";
    private static final String TOTAL_COUNT_BY_USER_ID = "SELECT COUNT(*) FROM application WHERE user_id = ?";
    private static final String SELECT_PAGE_BY_USER_ID = "SELECT * FROM application WHERE user_id = ? LIMIT ? OFFSET ?";
    private static final String FIND_ALL_APPLICATION_BY_ROOM_ID_AND_TIME_PERIOD = "SELECT * FROM application WHERE (room_id = ?) AND (check_in BETWEEN ? AND ?)";
    private static final String FIND_ALL_APPLICATION_BY_USER_ID_AND_TIME_PERIOD = "SELECT * FROM application WHERE (user_id = ?) AND (check_in > ?)";

    protected ApplicationDaoImpl(Connection connection, RowMapper<Application> rowMapper, Parser<Application> parser, String table, QueryCreator queryCreator) {
        super(connection, rowMapper, parser, table, queryCreator);
    }

    @Override
    public int differenceBetweenDate(Long id) throws SQLException {
        Object[] list = {id};
        PreparedStatement preparedStatement = createStatement(DIFFERENCE_DATE_QUERY, list);
        ResultSet rs = preparedStatement.executeQuery();
        int differenceDays = 0;
        if (rs.next()) {
            differenceDays = rs.getInt("difference");
        }
        return differenceDays;
    }

    @Override
    public Long findRowCount() throws DaoException {
        Optional<Long> records = selectCountRecords(TOTAL_COUNT);
        return records.orElse(0L);
    }

    @Override
    public List<Application> findPaginatePage(int items, int begin) throws DaoException {
        return executeQuery(SELECT_PAGE, items, begin);
    }

    @Override
    public List<Application> findLimitApplicationsInProgress(int items, int begin) throws DaoException {
        return executeQuery(SELECT_PAGE_IN_PROGRESS, items, begin);
    }

    @Override
    public Long findNumberRecordsInProgress() throws DaoException {
        Optional<Long> records = selectCountRecords(TOTAL_COUNT_IN_PROGRESS);
        return records.orElse(0L);
    }

    @Override
    public Long findNumberRecordsByUserId(Long id) throws DaoException {
        Optional<Long> records = selectCountRecords(TOTAL_COUNT_BY_USER_ID, id);
        return records.orElse(0L);
    }

    @Override
    public List<Application> findLimitByUserId(long id, int items, int begin) throws DaoException {
        return executeQuery(SELECT_PAGE_BY_USER_ID, id, items, begin);
    }

    @Override
    public List<Application> findAllByRoomIdAndTimePeriod(Long id, LocalDate begin, LocalDate end) throws DaoException {
        return executeQuery(FIND_ALL_APPLICATION_BY_ROOM_ID_AND_TIME_PERIOD,id,begin,end);
    }

    @Override
    public List<Application> findAllByUserIdAndTimePeriod(Long id, LocalDate begin) throws DaoException {
        return executeQuery(FIND_ALL_APPLICATION_BY_USER_ID_AND_TIME_PERIOD,id,begin);
    }

}
