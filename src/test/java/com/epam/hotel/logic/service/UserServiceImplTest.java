package com.epam.hotel.logic.service;

import com.epam.hotel.dao.ApplicationDaoImpl;
import com.epam.hotel.dao.DaoHelper;
import com.epam.hotel.dao.DaoHelperFactory;
import com.epam.hotel.dao.api.ApplicationDao;
import com.epam.hotel.dao.api.UserDao;
import com.epam.hotel.entity.Application;
import com.epam.hotel.entity.User;
import com.epam.hotel.entity.enums.ApplicationStatus;
import com.epam.hotel.entity.enums.RoomType;
import com.epam.hotel.entity.enums.UserRole;
import com.epam.hotel.entity.enums.UserStatus;
import com.epam.hotel.exception.DaoException;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.api.UserService;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {
    private static final String LOGIN_VALUE = "login";
    private static final String PASSWORD_VALUE = "password";
    private static final long USER_ID = 1L;

    private final DaoHelperFactory daoHelperFactory = mock(DaoHelperFactory.class);
    private final DaoHelper daoHelper = mock(DaoHelper.class);
    private final UserDao userDao = Mockito.mock(UserDao.class);
    private final ApplicationDao applicationDao = mock(ApplicationDaoImpl.class);
    UserService userService = new UserServiceImpl(daoHelperFactory);

    @BeforeMethod
    public void createUserDaoAndDaoHelper() throws DaoException {
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createUserDao()).thenReturn(userDao);
        when(daoHelper.createApplicationDao()).thenReturn(applicationDao);
    }

    @Test
    public void testLoginShouldReturnUserByLoginAndPassword() throws ServicesException, DaoException {
        User user = new User(1L, LOGIN_VALUE, PASSWORD_VALUE, UserRole.ADMINISTRATOR, UserStatus.ACTIVE);
        when(userDao.findUserByLoginAndPassword(anyString(), anyString())).thenReturn(Optional.of(user));
        Optional<User> expectedUser = Optional.of(user);
        Optional<User> actualUser = userService.login(LOGIN_VALUE, PASSWORD_VALUE);

        Assert.assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testLoginShouldReturnEmptyOptionalWhenUserNotExist() throws ServicesException, DaoException {
        when(userDao.findUserByLoginAndPassword(anyString(), anyString())).thenReturn(Optional.empty());
        Optional<User> expectedUser = Optional.empty();

        Optional<User> actualUser = userService.login(LOGIN_VALUE, PASSWORD_VALUE);
        Assert.assertEquals(expectedUser, actualUser);
    }

    @Test(expectedExceptions = ServicesException.class)
    public void TestBlockUnblockUserShouldThrowExceptionWhenUserNotExist() throws DaoException, ServicesException {
        when(userDao.findById(USER_ID)).thenReturn(Optional.empty());

        userService.blockUnblockUser(USER_ID);
    }

    @Test
    public void TestBlockUnblockUserShouldBlockUserWhenUserActive() throws DaoException, ServicesException, SQLException {
        User expectedUser = new User(USER_ID, LOGIN_VALUE, PASSWORD_VALUE, UserRole.GUEST, UserStatus.BLOCKED);
        User actualUser = new User(USER_ID, LOGIN_VALUE, PASSWORD_VALUE, UserRole.GUEST, UserStatus.ACTIVE);
        when(userDao.findById(USER_ID)).thenReturn(Optional.of(actualUser));

        userService.blockUnblockUser(USER_ID);

        verify(userDao, times(1)).save(actualUser);
        Assert.assertEquals(actualUser, expectedUser);
    }

    @Test
    public void TestBlockUnblockUserShouldUnblockUserWhenUserBlocked() throws DaoException, ServicesException, SQLException {
        User expectedUser = new User(USER_ID, LOGIN_VALUE, PASSWORD_VALUE, UserRole.GUEST, UserStatus.ACTIVE);
        User actualUser = new User(USER_ID, LOGIN_VALUE, PASSWORD_VALUE, UserRole.GUEST, UserStatus.BLOCKED);

        when(userDao.findById(USER_ID)).thenReturn(Optional.of(actualUser));

        userService.blockUnblockUser(USER_ID);

        verify(userDao, times(1)).save(actualUser);
        Assert.assertEquals(actualUser, expectedUser);
    }

    @Test(expectedExceptions = ServicesException.class)
    public void TestDeleteUserShouldThrowExceptionWhenUserNotExist() throws DaoException, ServicesException, SQLException {
        when(userDao.findById(USER_ID)).thenReturn(Optional.empty());

        userService.deleteUser(USER_ID);
    }

    @Test
    public void testDeleteUserShouldDeleteUser() throws DaoException, ServicesException, SQLException {
        Application firstApplication = new Application(1L, LocalDate.now(), LocalDate.now(), RoomType.COMFORT, (byte) 3, ApplicationStatus.CONFIRMED, USER_ID, 3L, new BigDecimal("100"));
        Application secondApplication = new Application(2L, LocalDate.now(), LocalDate.now(), RoomType.PRESIDENT, (byte) 2, ApplicationStatus.CONFIRMED, USER_ID, 5L, new BigDecimal("250"));
        List<Application> actualApplicationList = Arrays.asList(firstApplication, secondApplication);

        Application thirdApplication = new Application(1L, LocalDate.now(), LocalDate.now(), RoomType.COMFORT, (byte) 3, ApplicationStatus.REJECTED, USER_ID, 0L, new BigDecimal("0"));
        Application fourthApplication = new Application(2L, LocalDate.now(), LocalDate.now(), RoomType.PRESIDENT, (byte) 2, ApplicationStatus.REJECTED, USER_ID, 0L, new BigDecimal("0"));
        List<Application> expectedApplicationList = Arrays.asList(thirdApplication, fourthApplication);

        User actualUser = new User(USER_ID, LOGIN_VALUE, PASSWORD_VALUE, UserRole.GUEST, UserStatus.ACTIVE);
        User expectedUser = new User(USER_ID, LOGIN_VALUE, PASSWORD_VALUE, UserRole.GUEST, UserStatus.DELETED);

        when(userDao.findById(USER_ID)).thenReturn(Optional.of(actualUser));
        when(applicationDao.findAllByUserIdAndTimePeriod(anyLong(), any(LocalDate.class))).thenReturn(actualApplicationList);
        userService.deleteUser(USER_ID);

        verify(userDao,times(1)).save(actualUser);
        verify(applicationDao,times(1)).save(firstApplication);
        verify(applicationDao,times(1)).save(secondApplication);
        Assert.assertEquals(actualUser, expectedUser);
        Assert.assertEquals(actualApplicationList, expectedApplicationList);
    }
}
