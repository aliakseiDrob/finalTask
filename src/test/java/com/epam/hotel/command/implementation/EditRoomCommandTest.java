package com.epam.hotel.command.implementation;

import com.epam.hotel.command.CommandResult;
import com.epam.hotel.entity.Room;
import com.epam.hotel.entity.enums.RoomStatus;
import com.epam.hotel.entity.enums.RoomType;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.api.RoomService;
import com.epam.hotel.logic.validators.api.RoomValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class EditRoomCommandTest {
    private static final String ROOM_ID = "roomId";
    private static final String ROOM_TYPE = "roomType";
    private static final String ROOM_PRICE = "price";
    private static final String ROOM_STATUS = "status";
    private static final String ROOM_CAPACITY = "capacity";
    private static final String ROOM_NUMBER = "number";
    private static final String ALL_ROOMS_COMMAND = "controller?command=allRooms";
    private static final String EDIT_ROOM_PAGE = "WEB-INF/view/editRoom.jsp";
    private static final String ERROR_MESSAGE_PARAM = "errorMessage";
    private static final String ERROR_MESSAGE_VALUE = "wrongParameters";
    private static final String VALID_ROOM_ID = "1";
    private static final String VALID_ROOM_TYPE = RoomType.COMFORT.toString();
    private static final String VALID_ROOM_PRICE = "100";
    private static final String VALID_ROOM_STATUS = RoomStatus.AVAILABLE.toString();
    private static final String VALID_ROOM_CAPACITY = "3";
    private static final String VALID_ROOM_NUMBER = "11";
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RoomService roomService;
    private EditRoomCommand editRoomCommand;
    private final RoomValidator roomValidator = mock(RoomValidator.class);


    @BeforeMethod
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        roomService = mock(RoomService.class);
        editRoomCommand = new EditRoomCommand(roomService, roomValidator);
        when(request.getParameter(ROOM_ID)).thenReturn(VALID_ROOM_ID);
        when(request.getParameter(ROOM_TYPE)).thenReturn(VALID_ROOM_TYPE);
        when(request.getParameter(ROOM_PRICE)).thenReturn(VALID_ROOM_PRICE);
        when(request.getParameter(ROOM_STATUS)).thenReturn(VALID_ROOM_STATUS);
        when(request.getParameter(ROOM_CAPACITY)).thenReturn(VALID_ROOM_CAPACITY);
        when(request.getParameter(ROOM_NUMBER)).thenReturn(VALID_ROOM_NUMBER);
    }

    @Test
    public void testExecuteShouldEditRoomWhenDataValid() throws ServicesException {
        when(roomValidator.isValidRoomType(anyString())).thenReturn(true);
        when(roomValidator.isValidRoomNumber(anyString())).thenReturn(true);
        when(roomValidator.isValidPrice(anyString())).thenReturn(true);
        when(roomValidator.isValidCapacity(anyString())).thenReturn(true);

        CommandResult expected = CommandResult.redirect(ALL_ROOMS_COMMAND);
        CommandResult actual = editRoomCommand.execute(request, response);

        verify(request, times(0)).setAttribute(anyString(), any());
        verify(roomService, times(1)).editRoom(any(Room.class));
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testExecuteShouldReturnForwardEditRoomPageWhenDataNotValid() throws ServicesException {
        when(roomValidator.isValidRoomType(anyString())).thenReturn(true);
        when(roomValidator.isValidRoomNumber(anyString())).thenReturn(true);
        when(roomValidator.isValidPrice(anyString())).thenReturn(true);
        when(roomValidator.isValidCapacity(anyString())).thenReturn(false);

        CommandResult expected = CommandResult.forward(EDIT_ROOM_PAGE);
        CommandResult actual = editRoomCommand.execute(request, response);

        verify(request, times(1)).setAttribute(ERROR_MESSAGE_PARAM, ERROR_MESSAGE_VALUE);
        verify(roomService, times(0)).editRoom(any(Room.class));
        Assert.assertEquals(actual, expected);

    }
}