package com.epam.hotel.logic.service;

import com.epam.hotel.dao.DaoHelper;
import com.epam.hotel.dao.DaoHelperFactory;
import com.epam.hotel.dao.api.ApplicationDao;
import com.epam.hotel.entity.Application;
import com.epam.hotel.entity.enums.ApplicationStatus;
import com.epam.hotel.entity.enums.RoomType;
import com.epam.hotel.exception.DaoException;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.api.ApplicationService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


public class ApplicationServiceImplTest {

    private static final long APPLICATION_ID = 1L;
    private static final Long USER_ID = 1L;
    private static final int DATE_DIFFERENCE = 2;
    private static final int ITEMS_PER_PAGE = 7;
    private static final Application NULL_APPLICATION = null;
    private static final int BEGIN_POSITION = 0;
    private static final int PAGE = 1;
    private static final long ROWS_COUNT_IN_TABLE = 10L;
    private final DaoHelper daoHelper = mock(DaoHelper.class);
    private final DaoHelperFactory daoHelperFactory = mock(DaoHelperFactory.class);
    private final ApplicationDao applicationDao = mock(ApplicationDao.class);
    private final ApplicationService applicationService = new ApplicationServiceImpl(daoHelperFactory);

    @BeforeMethod
    public void setUp() throws DaoException {
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createApplicationDao()).thenReturn(applicationDao);
    }


    @Test(expectedExceptions = ServicesException.class)
    public void testFindByIdShouldReturnServicesExceptionWhenApplicationNotExist() throws DaoException, ServicesException {
        when(applicationDao.findById(APPLICATION_ID)).thenReturn(Optional.empty());
        applicationService.findById(APPLICATION_ID);
    }

    @Test
    public void testFindByIdShouldReturnApplication() throws DaoException, ServicesException {
        LocalDate checkIn = LocalDate.now();
        LocalDate checkOut = checkIn.plusDays(2);
        Application application = new Application(1L, checkIn, checkOut, RoomType.COMFORT, (byte) 4, ApplicationStatus.IN_PROGRESS, USER_ID, 0L, null);
        when(applicationDao.findById(APPLICATION_ID)).thenReturn(Optional.of(application));

        Application actual = applicationService.findById(APPLICATION_ID);
        Assert.assertEquals(actual, application);
    }

    @Test
    public void testSaveApplicationShouldSaveApplication() throws ServicesException, DaoException {
        LocalDate checkIn = LocalDate.now();
        LocalDate checkOut = checkIn.plusDays(2);
        Application application = new Application(1L, checkIn, checkOut, RoomType.COMFORT, (byte) 4, ApplicationStatus.IN_PROGRESS, USER_ID, 0L, null);
        applicationService.saveApplication(application);
        verify(applicationDao, times(1)).save(application);
    }

    @Test
    public void testGetDifferenceDate() throws DaoException, ServicesException {
        when(applicationDao.differenceBetweenDate(APPLICATION_ID)).thenReturn(DATE_DIFFERENCE);
        int actualDifference = applicationService.getDifferenceDate(APPLICATION_ID);
        Assert.assertEquals(actualDifference, DATE_DIFFERENCE);
    }

    @Test
    public void testFindAmountPagesShouldReturnAmountPages() throws DaoException, ServicesException {
        when(applicationDao.findRowCount()).thenReturn(ROWS_COUNT_IN_TABLE);
        long expectedAmountPages = 2L;
        long actualAmountPages = applicationService.findAmountPages(ITEMS_PER_PAGE);
        Assert.assertEquals(actualAmountPages, expectedAmountPages);
    }

    @Test
    public void testFindPageApplicationsShouldReturnListApplications() throws DaoException, ServicesException {
        List<Application> expectedList = Arrays.asList(NULL_APPLICATION, NULL_APPLICATION);
        when(applicationDao.findPaginatePage(ITEMS_PER_PAGE, BEGIN_POSITION)).thenReturn(expectedList);
        List<Application> actualList = applicationService.findPageApplications(ITEMS_PER_PAGE, PAGE);
        Assert.assertEquals(actualList, expectedList);
    }
    @Test
    public void testFindAmountPagesInProgressShouldReturnAmountPages() throws DaoException, ServicesException {
        when(applicationDao.findRowCountInProgress()).thenReturn(ROWS_COUNT_IN_TABLE);
        long expectedAmountPages = 2L;
        long actualAmountPages = applicationService.findAmountPagesInProgress(ITEMS_PER_PAGE);
        Assert.assertEquals(actualAmountPages, expectedAmountPages);
    }
    @Test
    public void testFindPageApplicationsInProgressShouldReturnListApplications() throws DaoException, ServicesException {
        List<Application> expectedList = Arrays.asList(NULL_APPLICATION, NULL_APPLICATION);
        when(applicationDao.findPaginatePageInProgress(ITEMS_PER_PAGE, BEGIN_POSITION)).thenReturn(expectedList);
        List<Application> actualList = applicationService.findPageApplicationsInProgress(ITEMS_PER_PAGE, PAGE);
        Assert.assertEquals(actualList, expectedList);
    }



    @Test
    public void testFindAmountPagesByUserIdShouldReturnAmountPages() throws DaoException, ServicesException {
        when(applicationDao.findRowCountByUserId(USER_ID)).thenReturn(ROWS_COUNT_IN_TABLE);
        long expectedAmountPages = 2L;
        long actualAmountPages = applicationService.findAmountPagesByUserId(USER_ID,ITEMS_PER_PAGE);
        Assert.assertEquals(actualAmountPages, expectedAmountPages);
    }

    @Test
    public void testFindPageByUserIdShouldReturnListApplications() throws DaoException, ServicesException {
        List<Application> expectedList = Arrays.asList(NULL_APPLICATION, NULL_APPLICATION);
        when(applicationDao.findPaginatePageByUserId(USER_ID,ITEMS_PER_PAGE, BEGIN_POSITION)).thenReturn(expectedList);
        List<Application> actualList = applicationService.findPageByUserId(USER_ID,ITEMS_PER_PAGE, PAGE);
        Assert.assertEquals(actualList, expectedList);
    }
}