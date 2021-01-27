package com.epam.hotel.command.implementation;

import com.epam.hotel.command.CommandResult;
import com.epam.hotel.dao.DaoHelperFactory;
import com.epam.hotel.entity.enums.RoomStatus;
import com.epam.hotel.entity.enums.RoomType;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.RoomServiceImpl;
import com.epam.hotel.logic.service.api.RoomService;
import com.epam.hotel.logic.validators.RoomValidatorImpl;
import com.epam.hotel.logic.validators.api.RoomValidator;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddRoomCommandTest {
    private static final String ROOM_ID = "roomId";
    private static final String ROOM_TYPE = "roomType";
    private static final String ROOM_PRICE = "price";
    private static final String ROOM_STATUS = "status";
    private static final String ROOM_CAPACITY = "capacity";
    private static final String ROOM_NUMBER = "number";
    private static final String ALL_ROOMS_COMMAND = "controller?command=allRooms";
    private static final String ADD_ROOM_PAGE = "WEB-INF/view/addRoom.jsp";
    private static final String VALID_ROOM_ID = "1";
    private static final String VALID_ROOM_PRICE = "225";
    private static final String VALID_ROOM_CAPACITY = "3";
    private static final String VALID_ROOM_NUMBER = "28";
    private static final String VALID_ROOM_STATUS = RoomStatus.AVAILABLE.toString();
    private static final String VALID_ROOM_TYPE = RoomType.COMFORT.toString();
    private static final String INVALID_ROOM_TYPE = "InvalidRoomType";
    private final RoomService roomService = new RoomServiceImpl(new DaoHelperFactory());
    private final RoomValidator roomValidator = new RoomValidatorImpl();
    private final AddRoomCommand addRoomCommand = new AddRoomCommand(roomService, roomValidator);
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeMethod
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    public void testExecuteShouldReturnAddRoomPageWhenDataNotValid() throws ServicesException {
        when(request.getParameter(ROOM_ID)).thenReturn(VALID_ROOM_ID);
        when(request.getParameter(ROOM_TYPE)).thenReturn(INVALID_ROOM_TYPE);
        when(request.getParameter(ROOM_PRICE)).thenReturn(VALID_ROOM_PRICE);
        when(request.getParameter(ROOM_STATUS)).thenReturn(VALID_ROOM_STATUS);
        when(request.getParameter(ROOM_CAPACITY)).thenReturn(VALID_ROOM_CAPACITY);
        when(request.getParameter(ROOM_NUMBER)).thenReturn(VALID_ROOM_NUMBER);

        CommandResult expected = CommandResult.forward(ADD_ROOM_PAGE);
        CommandResult actual = addRoomCommand.execute(request, response);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testExecuteShouldReturnCommandAllRoomsWhenDataValid() throws ServicesException {
        when(request.getParameter(ROOM_ID)).thenReturn(VALID_ROOM_ID);
        when(request.getParameter(ROOM_TYPE)).thenReturn(VALID_ROOM_TYPE);
        when(request.getParameter(ROOM_PRICE)).thenReturn(VALID_ROOM_PRICE);
        when(request.getParameter(ROOM_STATUS)).thenReturn(VALID_ROOM_STATUS);
        when(request.getParameter(ROOM_CAPACITY)).thenReturn(VALID_ROOM_CAPACITY);
        when(request.getParameter(ROOM_NUMBER)).thenReturn(VALID_ROOM_NUMBER);

        CommandResult expected = CommandResult.redirect(ALL_ROOMS_COMMAND);
        CommandResult actual = addRoomCommand.execute(request, response);

        Assert.assertEquals(expected, actual);
    }
}
