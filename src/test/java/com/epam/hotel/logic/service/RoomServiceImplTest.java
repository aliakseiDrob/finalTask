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
import com.epam.hotel.logic.service.api.RoomService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class RoomServiceImplTest {
    private static final long ROOM_ID = 1L;
    private static final Long USER_ID = 1L;
    private static final Room ROOM_NULL = null;
    private static final int ITEMS_PER_PAGE = 7;
    private static final long ROWS_IN_TABLE = 10L;
    private static final int BEGIN_SEARCH_POSITION = 0;
    private static final int PAGE = 1;
    private final Room ROOM = new Room(1L, RoomType.COMFORT, new BigDecimal("300"), RoomStatus.AVAILABLE, (byte) 4, 22);
    private final DaoHelper daoHelper = mock(DaoHelper.class);
    private final DaoHelperFactory daoHelperFactory = mock(DaoHelperFactory.class);
    private RoomDao roomDao;
    private final ApplicationDao applicationDao = mock(ApplicationDao.class);
    private final RoomService roomService = new RoomServiceImpl(daoHelperFactory);

    @BeforeMethod
    public void setUp() throws DaoException {
        roomDao = mock(RoomDao.class);
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createApplicationDao()).thenReturn(applicationDao);
        when(daoHelper.createRoomDao()).thenReturn(roomDao);
    }


    @Test
    public void testAddRoomShouldSaveRoomInDataBase() throws DaoException, ServicesException {
        roomService.addRoom(ROOM);
        verify(roomDao, times(1)).save(ROOM);
    }

    @Test(expectedExceptions = ServicesException.class)
    public void testBlockUnblockRoomShouldReturnServicesExceptionWhenRoomNotExist() throws DaoException, ServicesException {
        when(roomDao.findById(ROOM_ID)).thenReturn(Optional.empty());
        roomService.blockUnblockRoom(ROOM_ID);
    }

    @Test
    public void testBlockUnblockRoomShouldBlockRoomWhenRoomAvailable() throws DaoException, ServicesException {
        Room availableRoom = new Room(1L, RoomType.COMFORT, new BigDecimal("300"), RoomStatus.AVAILABLE, (byte) 4, 22);
        Room unavailableRoom = new Room(1L, RoomType.COMFORT, new BigDecimal("300"), RoomStatus.UNAVAILABLE, (byte) 4, 22);
        when(roomDao.findById(ROOM_ID)).thenReturn(Optional.of(availableRoom));
        roomService.blockUnblockRoom(ROOM_ID);
        verify(roomDao, times(1)).save(availableRoom);
        Assert.assertEquals(availableRoom, unavailableRoom);
    }

    @Test
    public void testBlockUnblockRoomShouldUnblockRoomWhenRoomUnavailable() throws DaoException, ServicesException {
        Room unavailableRoom = new Room(1L, RoomType.COMFORT, new BigDecimal("300"), RoomStatus.UNAVAILABLE, (byte) 4, 22);
        Room availableRoom = new Room(1L, RoomType.COMFORT, new BigDecimal("300"), RoomStatus.AVAILABLE, (byte) 4, 22);
        when(roomDao.findById(ROOM_ID)).thenReturn(Optional.of(unavailableRoom));
        roomService.blockUnblockRoom(ROOM_ID);
        verify(roomDao, times(1)).save(unavailableRoom);
        Assert.assertEquals(unavailableRoom, availableRoom);
    }

    @Test
    public void testEditRoomShouldSaveEditedRoomAndChangeAllApplicationsContainsEditedRoom() throws DaoException, ServicesException {
        Application firstApplication = new Application(1L, LocalDate.now(), LocalDate.now(), RoomType.COMFORT, (byte) 4, ApplicationStatus.CONFIRMED, USER_ID, ROOM_ID, new BigDecimal("100"));
        Application secondApplication = new Application(2L, LocalDate.now(), LocalDate.now(), RoomType.LUX, (byte) 2, ApplicationStatus.CONFIRMED, USER_ID, ROOM_ID, new BigDecimal("100"));
        List<Application> listBeforeEditRoom = Arrays.asList(firstApplication, secondApplication);
        Application thirdApplication = new Application(1L, LocalDate.now(), LocalDate.now(), RoomType.COMFORT, (byte) 4, ApplicationStatus.IN_PROGRESS, USER_ID, 0L, new BigDecimal("0"));
        Application fourthApplication = new Application(2L, LocalDate.now(), LocalDate.now(), RoomType.LUX, (byte) 2, ApplicationStatus.IN_PROGRESS, USER_ID, 0L, new BigDecimal("0"));
        List<Application> listAfterEditRoom = Arrays.asList(thirdApplication, fourthApplication);

        when(applicationDao.findAllByRoomIdAndTimePeriod(anyLong(), any(LocalDate.class), any(LocalDate.class))).thenReturn(listBeforeEditRoom);
        roomService.editRoom(ROOM);

        verify(roomDao, times(1)).save(ROOM);
        verify(applicationDao, times(1)).save(firstApplication);
        verify(applicationDao, times(1)).save(secondApplication);
        Assert.assertEquals(listBeforeEditRoom, listAfterEditRoom);
    }

    @Test
    public void testGetAllAvailableRoomsShouldReturnListRooms() throws DaoException, ServicesException {
        List<Room> expectedRoomList = Arrays.asList(ROOM_NULL, ROOM_NULL);
        when(roomDao.findAllAvailableRooms(any(LocalDate.class), any(LocalDate.class))).thenReturn(expectedRoomList);
        List<Room> actualRoomList = roomService.getAllAvailableRooms(LocalDate.now(), LocalDate.now());

        Assert.assertEquals(actualRoomList, expectedRoomList);
    }

    @Test(expectedExceptions = ServicesException.class)
    public void testFindByIdShouldThrowExceptionWhenRoomNotExist() throws DaoException, ServicesException {
        when(roomDao.findById(anyLong())).thenReturn(Optional.empty());
        roomService.findById(ROOM_ID);
    }

    @Test
    public void testFindByIdShouldReturnRoom() throws DaoException, ServicesException {
        when(roomDao.findById(anyLong())).thenReturn(Optional.of(ROOM));
        Room actualRoom = roomService.findById(ROOM_ID);
        Assert.assertEquals(actualRoom, ROOM);
    }

    @Test
    public void testFindAmountPagesShouldReturnAmountPages() throws DaoException, ServicesException {
        long expectedAmountPages = 2L;
        when(roomDao.findRowCount()).thenReturn(ROWS_IN_TABLE);
        long actualAmountPages = roomService.findAmountPages(ITEMS_PER_PAGE);
        Assert.assertEquals(actualAmountPages, expectedAmountPages);
    }

    @Test
    public void testFindPageRoomsShouldReturnListRooms() throws DaoException, ServicesException {
        List<Room> expectedRoomList = Arrays.asList(ROOM_NULL, ROOM_NULL, ROOM_NULL);
        when(roomDao.findPaginatePage(ITEMS_PER_PAGE, BEGIN_SEARCH_POSITION)).thenReturn(expectedRoomList);
        List<Room> actualRoomList = roomService.findPageRooms(ITEMS_PER_PAGE, PAGE);
        Assert.assertEquals(actualRoomList, expectedRoomList);
    }
}