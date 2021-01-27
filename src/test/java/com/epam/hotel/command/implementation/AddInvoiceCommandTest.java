package com.epam.hotel.command.implementation;

import com.epam.hotel.command.CommandResult;
import com.epam.hotel.dao.DaoHelperFactory;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.ApplicationRoomServiceImpl;
import com.epam.hotel.logic.service.api.ApplicationRoomService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddInvoiceCommandTest {
    private static final String COMMAND_IN_PROGRESS_APPLICATION = "controller?command=inProgressApplication";
    private static final String ROOM_ID = "roomId";
    private static final String APPLICATION_ID = "applicationId";
    private static final String ROOM_ID_VALUE = "1";
    private static final String APPLICATION_ID_VALUE = "2";
    private HttpServletRequest request;
    private HttpServletResponse response;
    private AddInvoiceCommand addInvoiceCommand ;

    @BeforeMethod
    public void setUp(){
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        ApplicationRoomService applicationRoomService = new ApplicationRoomServiceImpl(new DaoHelperFactory());
        addInvoiceCommand = new AddInvoiceCommand(applicationRoomService);
    }
    @Test
    public void testExecuteShouldReturnForwardCorrectPage() throws ServicesException {
        CommandResult expectedCommandResult = CommandResult.forward(COMMAND_IN_PROGRESS_APPLICATION);
        when(request.getParameter(ROOM_ID)).thenReturn(ROOM_ID_VALUE);
        when(request.getParameter(APPLICATION_ID)).thenReturn(APPLICATION_ID_VALUE);
        CommandResult actualCommandResult = addInvoiceCommand.execute(request,response);
        Assert.assertEquals(expectedCommandResult,actualCommandResult);
    }
}
