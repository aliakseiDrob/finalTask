package com.epam.hotel.logic.service;

import com.epam.hotel.dao.DaoHelper;
import com.epam.hotel.dao.DaoHelperFactory;
import com.epam.hotel.dao.api.ApplicationDao;
import com.epam.hotel.dao.api.RoomDao;
import com.epam.hotel.entity.Application;
import com.epam.hotel.entity.Room;
import com.epam.hotel.entity.enums.ApplicationStatus;
import com.epam.hotel.entity.enums.RoomStatus;
import com.epam.hotel.entity.enums.RoomType;
import com.epam.hotel.exception.DaoException;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.api.ApplicationRoomService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class ApplicationRoomServiceImplTest {
    private static final long ROOM_ID = 1L;
    private static final long APPLICATION_ID = 1L;
    private static final Long USER_ID = 1L;
    private final Room ROOM = new Room(1L, RoomType.COMFORT,new BigDecimal("300"), RoomStatus.AVAILABLE,(byte) 4,22);
    private final DaoHelper daoHelper = mock(DaoHelper.class);
    private final DaoHelperFactory daoHelperFactory = mock(DaoHelperFactory.class);
    private final RoomDao roomDao = mock(RoomDao.class);
    private final ApplicationDao applicationDao = mock(ApplicationDao.class);
    private final ApplicationRoomService applicationRoomService = new ApplicationRoomServiceImpl(daoHelperFactory);

    @BeforeMethod
    public void setUp() throws DaoException {
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createApplicationDao()).thenReturn(applicationDao);
        when(daoHelper.createRoomDao()).thenReturn(roomDao);
    }


    @Test (expectedExceptions = ServicesException.class)
    public void testCreateInvoiceShouldThrowServicesExceptionWhenRoomNotExist() throws DaoException, ServicesException {
        when(roomDao.findById(ROOM_ID)).thenReturn(Optional.empty());
        applicationRoomService.createInvoice(ROOM_ID, APPLICATION_ID);
    }
    @Test (expectedExceptions = ServicesException.class)
    public void testCreateInvoiceShouldThrowServicesExceptionWhenApplicationNotExist() throws DaoException, ServicesException {
        when(roomDao.findById(ROOM_ID)).thenReturn(Optional.of(ROOM));
        when(applicationDao.findById(APPLICATION_ID)).thenReturn(Optional.empty());
        applicationRoomService.createInvoice(ROOM_ID, APPLICATION_ID);
    }
    @Test
    public void testCreateInvoiceShouldChangeApplicationWhenRoomAndApplicationExist() throws DaoException, ServicesException, SQLException {
        LocalDate checkIn = LocalDate.now();
        LocalDate checkOut = checkIn.plusDays(2);
        Application inProgressApplication = new Application(1L,checkIn,checkOut,RoomType.COMFORT,(byte) 4, ApplicationStatus.IN_PROGRESS,USER_ID,0L,null);
        Application confirmedApplication = new Application(1L,checkIn,checkOut,RoomType.COMFORT,(byte) 4, ApplicationStatus.CONFIRMED,USER_ID,ROOM_ID,new BigDecimal("600"));

        when(roomDao.findById(ROOM_ID)).thenReturn(Optional.of(ROOM));
        when(applicationDao.findById(APPLICATION_ID)).thenReturn(Optional.of(inProgressApplication));
        when(applicationDao.differenceBetweenDate(APPLICATION_ID)).thenReturn(2);
        applicationRoomService.createInvoice(ROOM_ID, APPLICATION_ID);

        verify(applicationDao,times(1)).differenceBetweenDate(APPLICATION_ID);
        verify(applicationDao,times(1)).save(inProgressApplication);

        Assert.assertEquals(inProgressApplication,confirmedApplication);
    }
}