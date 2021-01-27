package com.epam.hotel.dao.mapper;

import com.epam.hotel.entity.User;
import com.epam.hotel.entity.enums.UserRole;
import com.epam.hotel.entity.enums.UserStatus;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_LOGIN = "login";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_ROLE = "role";
    private static final String COLUMN_STATUS = "status";

    @Override
    public User map(ResultSet resultSet) throws SQLException {

        Long id = resultSet.getLong(COLUMN_ID);
        String login = resultSet.getString(COLUMN_LOGIN);
        String password = resultSet.getString(COLUMN_PASSWORD);
        String role = resultSet.getString(COLUMN_ROLE);
        UserRole userRole = UserRole.valueOf(role);
        String status = resultSet.getString(COLUMN_STATUS);
        UserStatus userStatus = UserStatus.valueOf(status);
        return new User(id, login, password, userRole, userStatus);
    }
}
