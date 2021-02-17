package com.epam.hotel.command.implementation;

import com.epam.hotel.command.CommandResult;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.api.RoomService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;


public class BlockUnblockRoomCommandTest {
    private static final String ALL_ROOMS_COMMAND = "controller?command=allRooms";
    private static final String PARAMETER_ROOM_ID = "roomId";
    private static final String VALID_ROOM_ID = "1";
    private static final String INVALID_ROOM_ID = "0";
    private HttpServletRequest request;
    private HttpServletResponse response;
    private final RoomService roomService = mock(RoomService.class);
    private final BlockUnblockRoomCommand blockUnblockRoomCommand = new BlockUnblockRoomCommand(roomService);

    @BeforeMethod
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    public void testExecuteShouldReturnRedirectAllRoomsCommand() throws ServicesException {
        when(request.getParameter(PARAMETER_ROOM_ID)).thenReturn(VALID_ROOM_ID);
        CommandResult expected = CommandResult.redirect(ALL_ROOMS_COMMAND);
        CommandResult actual = blockUnblockRoomCommand.execute(request, response);
        verify(roomService, times(1)).blockUnblockRoom(anyLong());
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ServicesException.class)
    public void testExecuteShouldThrowServicesExceptionWhenRoomNotExist() throws ServicesException {
        when(request.getParameter(PARAMETER_ROOM_ID)).thenReturn(INVALID_ROOM_ID);
        doThrow(new ServicesException()).when(roomService).blockUnblockRoom(anyLong());
        blockUnblockRoomCommand.execute(request, response);
        verify(roomService, times(1)).blockUnblockRoom(anyLong());
    }

}