package com.epam.hotel.dao.mapper;

import com.epam.hotel.entity.dto.UserDto;
import com.epam.hotel.entity.enums.UserRole;
import com.epam.hotel.entity.enums.UserStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDtoRowMapper implements RowMapper<UserDto> {
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_LOGIN = "login";
    private static final String COLUMN_ROLE = "role";
    private static final String COLUMN_STATUS = "status";

    @Override
    public UserDto map(ResultSet resultSet) throws SQLException {

        Long id = resultSet.getLong(COLUMN_ID);
        String login = resultSet.getString(COLUMN_LOGIN);
        String role = resultSet.getString(COLUMN_ROLE);
        UserRole userRole = UserRole.valueOf(role);
        String status = resultSet.getString(COLUMN_STATUS);
        UserStatus userStatus = UserStatus.valueOf(status);
        return new UserDto(id, login, userRole, userStatus);
    }
}
