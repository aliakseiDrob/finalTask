package com.epam.hotel.command.implementation;

import com.epam.hotel.command.CommandResult;
import com.epam.hotel.entity.Application;
import com.epam.hotel.entity.enums.ApplicationStatus;
import com.epam.hotel.entity.enums.RoomType;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.ApplicationServiceImpl;
import com.epam.hotel.logic.service.RoomServiceImpl;
import com.epam.hotel.logic.service.UserDtoServiceImpl;
import com.epam.hotel.logic.service.api.ApplicationService;
import com.epam.hotel.logic.service.api.RoomService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class AvailableRoomsCommandTest {
    private static final String APPLICATION_ID = "applicationId";
    private static final String VALID_APPLICATION_ID = "1";
    private static final String ADD_INVOICE_PAGE = "WEB-INF/view/addInvoice.jsp";
    private static final Application APPLICATION = new Application(1L, LocalDate.now(), LocalDate.now(), RoomType.COMFORT, (byte) 3, ApplicationStatus.IN_PROGRESS, 2L, 0L, new BigDecimal("0"));
    private static final String INVALID_APPLICATION_ID = "4";
    private HttpServletRequest request;
    private HttpServletResponse response;
    private ApplicationService applicationService;
    private RoomService roomService;
    private AvailableRoomsCommand availableRoomsCommand;

    @BeforeMethod
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        applicationService = mock(ApplicationService.class);
        roomService = mock(RoomService.class);
        availableRoomsCommand = new AvailableRoomsCommand(applicationService, roomService);
    }

    @Test
    public void testExecuteShouldReturnForwardAddInvoicePage() throws ServicesException {
        when(request.getParameter(APPLICATION_ID)).thenReturn(VALID_APPLICATION_ID);
        when(applicationService.findById(1L)).thenReturn(APPLICATION);
        when(roomService.getAllAvailableRooms(LocalDate.now(), LocalDate.now())).thenReturn(new ArrayList<>());
        CommandResult expected = CommandResult.forward(ADD_INVOICE_PAGE);
        CommandResult actual = availableRoomsCommand.execute(request, response);
        Assert.assertEquals(expected, actual);
    }

    @Test(expectedExceptions = ServicesException.class)
    public void testExecuteShouldThrowServicesExceptionWhenIdNotValid() throws ServicesException {
        when(request.getParameter(APPLICATION_ID)).thenReturn(INVALID_APPLICATION_ID);
        when(applicationService.findById(4L)).thenThrow(new ServicesException());

        CommandResult actual = availableRoomsCommand.execute(request, response);

    }
}