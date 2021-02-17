package com.epam.hotel.command.implementation;

import com.epam.hotel.command.CommandResult;
import com.epam.hotel.entity.Application;
import com.epam.hotel.entity.enums.ApplicationStatus;
import com.epam.hotel.entity.enums.RoomType;
import com.epam.hotel.exception.ServicesException;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class AvailableRoomsCommandTest {
    private static final String APPLICATION_ID = "applicationId";
    private static final String VALID_APPLICATION_ID = "1";
    private static final String ADD_INVOICE_PAGE = "WEB-INF/view/addInvoice.jsp";
    private static final Application APPLICATION = new Application(1L, LocalDate.now(), LocalDate.now(), RoomType.COMFORT, (byte) 3, ApplicationStatus.IN_PROGRESS, 2L, 0L, new BigDecimal("0"));
    private static final String INVALID_APPLICATION_ID = "4";
    private static final long INVALID_ID = 4L;
    private final ApplicationService applicationService = mock(ApplicationService.class);
    private final RoomService roomService = mock(RoomService.class);
    private final AvailableRoomsCommand availableRoomsCommand = new AvailableRoomsCommand(applicationService, roomService);
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeMethod
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    public void testExecuteShouldReturnForwardAddInvoicePage() throws ServicesException {
        when(request.getParameter(APPLICATION_ID)).thenReturn(VALID_APPLICATION_ID);
        when(applicationService.findById(anyLong())).thenReturn(APPLICATION);
        when(roomService.getAllAvailableRooms(any(LocalDate.class), any(LocalDate.class))).thenReturn(new ArrayList<>());

        CommandResult expected = CommandResult.forward(ADD_INVOICE_PAGE);
        CommandResult actual = availableRoomsCommand.execute(request, response);

        Assert.assertEquals(actual, expected);
        verify(request, times(2)).setAttribute(anyString(), any());
    }

    @Test(expectedExceptions = ServicesException.class)
    public void testExecuteShouldThrowServicesExceptionWhenIdNotValid() throws ServicesException {
        when(request.getParameter(APPLICATION_ID)).thenReturn(INVALID_APPLICATION_ID);
        when(applicationService.findById(INVALID_ID)).thenThrow(new ServicesException());

        availableRoomsCommand.execute(request, response);
        verify(request, times(0)).setAttribute(anyString(), any());
    }
}