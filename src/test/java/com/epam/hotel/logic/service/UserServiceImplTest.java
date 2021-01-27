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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {
    private DaoHelperFactory daoHelperFactory;
    private UserDao userDao;
    ApplicationDao applicationDao;
    private DaoHelper daoHelper;
    private static final User FIRST_USER = new User(1L, "login", "password", UserRole.ADMINISTRATOR, UserStatus.ACTIVE);
    private static final User BLOCKED_USER = new User(1L, "login", "password", UserRole.ADMINISTRATOR, UserStatus.BLOCKED);
    private static final User UNBLOCKED_USER = new User(1L, "login", "password", UserRole.ADMINISTRATOR, UserStatus.ACTIVE);
    private static final User DELETED_USER = new User(1L, "login", "password", UserRole.ADMINISTRATOR, UserStatus.DELETED);
    Application firstApplication = new Application(1L, LocalDate.now(), LocalDate.now(), RoomType.COMFORT, (byte) 3, ApplicationStatus.REJECTED, 1L, 0L, new BigDecimal("0"));
    Application secondApplication = new Application(2L, LocalDate.now(), LocalDate.now(), RoomType.PRESIDENT, (byte) 2, ApplicationStatus.REJECTED, 1L, 0L, new BigDecimal("0"));
    List<Application> expectedApplicationList = Arrays.asList(firstApplication, secondApplication);


    @BeforeMethod
    public void createUserDaoAndDaoHelper() throws DaoException {
        daoHelperFactory = mock(DaoHelperFactory.class);
        DaoHelper daoHelper = mock(DaoHelper.class);
        userDao = Mockito.mock(UserDao.class);
        applicationDao = mock(ApplicationDaoImpl.class);
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createUserDao()).thenReturn(userDao);
        when(daoHelper.createApplicationDao()).thenReturn(applicationDao);
    }

    @Test
    public void testLoginShouldReturnUserByLoginAndPassword() throws ServicesException, DaoException {
        UserService userService = new UserServiceImpl(daoHelperFactory);
        Optional<User> expectedUser = Optional.of(FIRST_USER);
        when(userDao.findUserByLoginAndPassword("login", "password")).thenReturn(Optional.of(FIRST_USER));
        Optional<User> actualUser = userService.login("login", "password");
        Assert.assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testLoginShouldReturnEmptyOptionalWhenUserNotExist() throws ServicesException, DaoException {
        UserService userService = new UserServiceImpl(daoHelperFactory);
        Optional<User> expectedUser = Optional.empty();
        when(userDao.findUserByLoginAndPassword("login", "password")).thenReturn(Optional.empty());
        Optional<User> actualUser = userService.login("login", "password");
        Assert.assertEquals(expectedUser, actualUser);
    }

    @Test
    public void TestBlockUnblockUserShouldBlockUserWhenUserUnblocked() throws DaoException, ServicesException {
        User actualUser = new User(1L, "login", "password", UserRole.ADMINISTRATOR, UserStatus.ACTIVE);
        UserService userService = new UserServiceImpl(daoHelperFactory);
        when(userDao.findById(1L)).thenReturn(Optional.of(actualUser));
        userService.blockUnblockUser(1L);
        Assert.assertEquals(actualUser, BLOCKED_USER);
    }

    @Test
    public void TestBlockUnblockUserShouldUnBlockUserWhenUserBlocked() throws DaoException, ServicesException {
        User actualUser = new User(1L, "login", "password", UserRole.ADMINISTRATOR, UserStatus.BLOCKED);
        UserService userService = new UserServiceImpl(daoHelperFactory);
        when(userDao.findById(1L)).thenReturn(Optional.of(actualUser));
        userService.blockUnblockUser(1L);
        Assert.assertEquals(actualUser, UNBLOCKED_USER);
    }

//    @Test
//    public void testDeleteUserShouldDeleteUser() throws DaoException, ServicesException {
//        Application firstApplication = new Application(1L, LocalDate.now(), LocalDate.now(), RoomType.COMFORT, (byte) 3, ApplicationStatus.CONFIRMED, 1L, 3L, new BigDecimal("100"));
//        Application secondApplication = new Application(2L, LocalDate.now(), LocalDate.now(), RoomType.PRESIDENT, (byte) 2, ApplicationStatus.CONFIRMED, 1L, 5L, new BigDecimal("250"));
//        List<Application> actualApplicationList = Arrays.asList(firstApplication, secondApplication);
//
//        User actualUser = new User(1L, "login", "password", UserRole.ADMINISTRATOR, UserStatus.ACTIVE);
//        UserService userService = new UserServiceImpl(daoHelperFactory);
//
//        when(userDao.findById(1L)).thenReturn(Optional.of(actualUser));
//        when(applicationDao.findAllByUserIdAndTimePeriod(1L, LocalDate.now())).thenReturn(actualApplicationList);
//        userService.deleteUser(1L);
//        Assert.assertEquals(actualUser, DELETED_USER);
//        Assert.assertEquals(actualApplicationList,expectedApplicationList);
//    }
}
