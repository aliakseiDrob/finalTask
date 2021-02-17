package com.epam.hotel.command.implementation;

import com.epam.hotel.command.CommandResult;
import com.epam.hotel.entity.Application;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.api.ApplicationService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class GuestApplicationsCommandTest {
    private static final String ALL_APPLICATION_PAGE = "WEB-INF/view/allApplications.jsp";
    private static final String USER_ID = "userId";
    private static final String ALL_APPLICATIONS = "allApplications";
    private static final String PARAMETER_PAGE = "page";
    private static final String COMMAND_VALUE = "anyCommand";
    private static final String PARAMETER_COMMAND = "command";
    private static final String AMOUNT_PAGES = "amountPages";
    private static final String VALID_PAGE_VALUE = "3";
    private static final String INVALID_PAGE_VALUE = "6";
    private static final long AMOUNT_PAGES_VALUE = 3L;
    private static final long USER_ID_VALUE = 1L;
    private static final String NULL_VALUE = null;
    private static final Application APPLICATION_NULL = null;
    private final ApplicationService applicationService = mock(ApplicationService.class);
    private final GuestApplicationsCommand guestApplicationsCommand = new GuestApplicationsCommand(applicationService);
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeMethod
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(USER_ID)).thenReturn(USER_ID_VALUE);
        when(request.getParameter(PARAMETER_COMMAND)).thenReturn(COMMAND_VALUE);
    }

    @Test(expectedExceptions = ServicesException.class)
    public void testExecuteShouldThrowExceptionWhenPageNotExist() throws ServicesException {
        when(request.getParameter(PARAMETER_PAGE)).thenReturn(INVALID_PAGE_VALUE);
        when(applicationService.findAmountPagesByUserId(anyLong(), anyInt())).thenReturn(AMOUNT_PAGES_VALUE);
        guestApplicationsCommand.execute(request, response);
    }

    @Test
    public void testExecuteShouldReturnForwardAllApplicationPageWhenDefaultPage() throws ServicesException {
        List<Application> applicationList = Arrays.asList(APPLICATION_NULL, APPLICATION_NULL);
        when(request.getParameter(PARAMETER_PAGE)).thenReturn(NULL_VALUE);
        when(applicationService.findAmountPagesByUserId(anyLong(), anyInt())).thenReturn(AMOUNT_PAGES_VALUE);
        when(applicationService.findPageByUserId(anyLong(), anyInt(), anyInt())).thenReturn(applicationList);

        CommandResult expected = CommandResult.forward(ALL_APPLICATION_PAGE);
        CommandResult actual = guestApplicationsCommand.execute(request, response);

        verify(request, times(1)).setAttribute(AMOUNT_PAGES, AMOUNT_PAGES_VALUE);
        verify(request, times(1)).setAttribute(PARAMETER_COMMAND, COMMAND_VALUE);
        verify(request, times(1)).setAttribute(ALL_APPLICATIONS, applicationList);
        Assert.assertEquals(actual, expected);
    }
    @Test
    public void testExecuteShouldReturnForwardAllApplicationPageWhenPageValid() throws ServicesException {
        List<Application> applicationList = Arrays.asList(APPLICATION_NULL,APPLICATION_NULL);
        when(request.getParameter(PARAMETER_PAGE)).thenReturn(VALID_PAGE_VALUE);
        when(applicationService.findAmountPagesByUserId(anyLong(), anyInt())).thenReturn(AMOUNT_PAGES_VALUE);
        when(applicationService.findPageByUserId(anyLong(), anyInt(), anyInt())).thenReturn(applicationList);

        CommandResult expected = CommandResult.forward(ALL_APPLICATION_PAGE);
        CommandResult actual = guestApplicationsCommand.execute(request, response);

        verify(request, times(1)).setAttribute(AMOUNT_PAGES, AMOUNT_PAGES_VALUE);
        verify(request, times(1)).setAttribute(PARAMETER_COMMAND, COMMAND_VALUE);
        verify(request, times(1)).setAttribute(ALL_APPLICATIONS, applicationList);
        Assert.assertEquals(actual, expected);
    }
}