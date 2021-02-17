package com.epam.hotel.dao.parsers;

import com.epam.hotel.entity.User;
import com.epam.hotel.entity.enums.UserRole;
import com.epam.hotel.entity.enums.UserStatus;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserParser implements Parser<User> {
    private static final String ID = "id";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";
    private static final String STATUS = "status";

    @Override
    public Map<String, Object> parse(User item) {
        Long id = item.getId();
        String login = item.getLogin();
        String password = item.getPassword();
        UserRole userRole = item.getRole();
        String role = userRole.toString();
        UserStatus userStatus = item.getStatus();
        String status = userStatus.toString();
        Map<String, Object> fieldsMap = new LinkedHashMap<>();
        fieldsMap.put(ID, id);
        fieldsMap.put(LOGIN, login);
        fieldsMap.put(PASSWORD, password);
        fieldsMap.put(ROLE, role);
        fieldsMap.put(STATUS, status);

        return fieldsMap;

    }
}
