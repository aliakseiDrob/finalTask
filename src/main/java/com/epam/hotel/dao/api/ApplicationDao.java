package com.epam.hotel.dao.api;

import com.epam.hotel.entity.Application;
import com.epam.hotel.exception.DaoException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface ApplicationDao extends PersistentDao<Application> {

    int differenceBetweenDate(Long id) throws SQLException;

    Long findNumberRecordsInProgress() throws DaoException;

    List<Application> findLimitApplicationsInProgress(int items, int begin) throws DaoException;

    Long findNumberRecordsByUserId(Long id) throws DaoException;

    List<Application> findLimitByUserId(long id, int items, int begin) throws DaoException;

    List<Application> findAllByRoomIdAndTimePeriod(Long id, LocalDate begin,LocalDate end) throws DaoException;

    List<Application> findAllByUserIdAndTimePeriod(Long id, LocalDate begin) throws DaoException;

}
