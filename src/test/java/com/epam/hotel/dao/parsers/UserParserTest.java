package com.epam.hotel.dao.parsers;

import com.epam.hotel.entity.User;
import com.epam.hotel.entity.enums.UserRole;
import com.epam.hotel.entity.enums.UserStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserParserTest {
    private static final String ID = "id";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";
    private static final String STATUS = "status";
    private static final String LOGIN_VALUE = "login";
    private static final String PASSWORD_VALUE = "password";
    private final UserParser userParser = new UserParser();

    @Test
    public void testParseShouldReturnCorrectMapParameters() {
        User user = new User(1L, LOGIN_VALUE, PASSWORD_VALUE, UserRole.GUEST, UserStatus.ACTIVE);
        Map<String, Object> expectedMap = createExpectedMap();
        Map<String, Object> actualMap = userParser.parse(user);
        Assert.assertEquals(expectedMap, actualMap);
    }

    private Map<String, Object> createExpectedMap() {
        Map<String, Object> expectedMap = new LinkedHashMap<>();
        expectedMap.put(ID, 1L);
        expectedMap.put(LOGIN, LOGIN_VALUE);
        expectedMap.put(PASSWORD, PASSWORD_VALUE);
        UserRole userRole = UserRole.GUEST;
        String role = userRole.toString();
        expectedMap.put(ROLE, role);
        UserStatus userStatus = UserStatus.ACTIVE;
        String status = userStatus.toString();
        expectedMap.put(STATUS, status);
        return expectedMap;
    }
}
