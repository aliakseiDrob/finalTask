package com.epam.hotel.command.implementation;

import com.epam.hotel.command.CommandResult;
import com.epam.hotel.entity.Room;
import com.epam.hotel.entity.enums.RoomStatus;
import com.epam.hotel.entity.enums.RoomType;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.api.RoomService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class EditRoomPageCommandTest {
    private static final String PARAMETER_ROOM_ID = "roomId";
    private static final String PARAMETER_ROOM = "room";
    private static final String EDIT_ROOM_PAGE = "WEB-INF/view/editRoom.jsp";
    private static final String ROOM_ID_VALUE = "1";
    private static final Room ROOM = new Room(1L, RoomType.COMFORT, new BigDecimal("100"), RoomStatus.AVAILABLE, (byte) 3, 21);
    private final RoomService roomService = mock(RoomService.class);
    private final EditRoomPageCommand editRoomPageCommand = new EditRoomPageCommand(roomService);
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeMethod
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        when(request.getParameter(PARAMETER_ROOM_ID)).thenReturn(ROOM_ID_VALUE);
    }

    @Test
    public void testExecuteShouldReturnForwardEdiRoomPageWhenRoomExist() throws ServicesException {
        when(roomService.findById(anyLong())).thenReturn(ROOM);

        CommandResult expected = CommandResult.forward(EDIT_ROOM_PAGE);
        CommandResult actual = editRoomPageCommand.execute(request, response);

        verify(request, times(1)).setAttribute(PARAMETER_ROOM, ROOM);
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ServicesException.class)
    public void testExecuteShouldThrowExceptionWhenRoomNotExist() throws ServicesException {
        when(roomService.findById(anyLong())).thenThrow(new ServicesException());

        editRoomPageCommand.execute(request, response);

        verify(request, times(0)).setAttribute(PARAMETER_ROOM, ROOM);
    }

}