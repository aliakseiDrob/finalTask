package com.epam.hotel.command.implementation;

import com.epam.hotel.command.CommandResult;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.api.ApplicationRoomService;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

public class AddInvoiceCommandTest {
    private static final String COMMAND_IN_PROGRESS_APPLICATION = "controller?command=inProgressApplication";
    private static final String ROOM_ID = "roomId";
    private static final String APPLICATION_ID = "applicationId";
    private static final String ROOM_ID_VALUE = "1";
    private static final String APPLICATION_ID_VALUE = "2";
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final ApplicationRoomService applicationRoomService = mock(ApplicationRoomService.class);
    private final AddInvoiceCommand addInvoiceCommand = new AddInvoiceCommand(applicationRoomService);

    @Test
    public void testExecuteShouldReturnForwardCorrectPage() throws ServicesException {
        when(request.getParameter(ROOM_ID)).thenReturn(ROOM_ID_VALUE);
        when(request.getParameter(APPLICATION_ID)).thenReturn(APPLICATION_ID_VALUE);

        CommandResult expected = CommandResult.forward(COMMAND_IN_PROGRESS_APPLICATION);
        CommandResult actual = addInvoiceCommand.execute(request, response);

        verify(applicationRoomService,times(1)).createInvoice(anyLong(),anyLong());
        Assert.assertEquals(actual, expected);
    }
}
