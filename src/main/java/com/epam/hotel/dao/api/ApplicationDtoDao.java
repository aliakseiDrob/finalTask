package com.epam.hotel.dao.api;


import com.epam.hotel.entity.dto.ApplicationDto;
import com.epam.hotel.exception.DaoException;

import java.util.List;

public interface ApplicationDtoDao extends Dao<ApplicationDto> {

    Long findNumberRecordsByUserId(long id) throws DaoException;

    List<ApplicationDto> findLimitByUserId(long id, int items, int begin) throws DaoException;
}
