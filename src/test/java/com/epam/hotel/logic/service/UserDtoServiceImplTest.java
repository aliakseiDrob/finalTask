package com.epam.hotel.logic.service;

import com.epam.hotel.dao.DaoHelper;
import com.epam.hotel.dao.DaoHelperFactory;
import com.epam.hotel.dao.api.UserDtoDao;
import com.epam.hotel.entity.Room;
import com.epam.hotel.entity.dto.UserDto;
import com.epam.hotel.exception.DaoException;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.api.UserDtoService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserDtoServiceImplTest {
    private static final Long ROWS_IN_TABLE = 10L;
    private static final int ITEMS_PER_PAGE = 7;
    private static final UserDto USER_DTO_NULL = null;
    private static final int BEGIN_SEARCH_POSITION = 0;
    private static final int PAGE = 1;
    private final DaoHelper daoHelper = mock(DaoHelper.class);
    private final DaoHelperFactory daoHelperFactory = mock(DaoHelperFactory.class);
    private final UserDtoService userDtoService = new UserDtoServiceImpl(daoHelperFactory);
    private UserDtoDao userDtoDao;

    @BeforeMethod
    public void setUp() throws DaoException {
        userDtoDao = mock(UserDtoDao.class);
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createUserDtoDao()).thenReturn(userDtoDao);
    }

    @Test
    public void testFindAmountPagesShouldReturnAmountPages() throws DaoException, ServicesException {
        long expectedAmountPages = 2L;
        when(userDtoDao.findRowCount()).thenReturn(ROWS_IN_TABLE);
        long actualAmountPages = userDtoService.findAmountPages(ITEMS_PER_PAGE);
        Assert.assertEquals(actualAmountPages, expectedAmountPages);
    }

    @Test
    public void testFindPageUsers() throws DaoException, ServicesException {
        List<UserDto> expectedRoomList = Arrays.asList(USER_DTO_NULL, USER_DTO_NULL, USER_DTO_NULL);
        when(userDtoDao.findPaginatePage(ITEMS_PER_PAGE, BEGIN_SEARCH_POSITION)).thenReturn(expectedRoomList);
        List<UserDto> actualRoomList = userDtoService.findPageUsers(ITEMS_PER_PAGE, PAGE);
        Assert.assertEquals(actualRoomList, expectedRoomList);
    }
}