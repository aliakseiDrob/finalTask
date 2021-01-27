package com.epam.hotel.dao;

import com.epam.hotel.dao.api.RoomDao;
import com.epam.hotel.entity.Room;
import com.epam.hotel.exception.DaoException;
import com.epam.hotel.dao.mapper.RowMapper;
import com.epam.hotel.dao.parsers.Parser;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class RoomDaoImpl extends AbstractPersistentDao<Room> implements RoomDao {
    private static final String SELECT_PAGE = "SELECT * FROM room LIMIT ? OFFSET ?";
    private static final String TOTAL_COUNT = "SELECT COUNT(*) FROM room";
    private static final String ALL_AVAILABLE_ROOMS = "SELECT * FROM room WHERE room.status='AVAILABLE' AND NOT EXISTS " +
            "( SELECT NULL FROM application WHERE application.room_id = room.id " +
            "AND (application.status = 'CONFIRMED' AND (( ? between application.check_in AND application.check_out) " +
            "OR ( ? between application.check_in AND application.check_out) " +
            "OR ((?<application.check_in) AND (?>application.check_out))) ))";

    public RoomDaoImpl(Connection connection, RowMapper<Room> rowMapper, Parser<Room> parser, String table, QueryCreator queryCreator) {
        super(connection, rowMapper, parser, table, queryCreator);

    }


    @Override
    public List<Room> findAllAvailableRooms(LocalDate check_in, LocalDate check_out) throws DaoException {
        return executeQuery(ALL_AVAILABLE_ROOMS, check_in, check_out, check_in, check_out);
    }

    @Override
    public List<Room> findPaginatePage(int items, int begin) throws DaoException {
        return executeQuery(SELECT_PAGE, items, begin);
    }

    @Override
    public Long findRowCount() throws DaoException {
        Optional<Long> records = selectCountRecords(TOTAL_COUNT);
        return records.orElse(0L);
    }
}
