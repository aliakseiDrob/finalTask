package com.epam.hotel.command.implementation;

import com.epam.hotel.command.CommandResult;
import com.epam.hotel.entity.Application;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.ApplicationServiceImpl;
import com.epam.hotel.logic.service.api.ApplicationService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AllApplicationsCommandTest {
    private static final String PARAMETER_PAGE = "page";
    private static final String PARAMETER_COMMAND = "command";
    private static final String ALL_APPLICATIONS_PAGE = "WEB-INF/view/allApplications.jsp";
    private static final int DEFAULT_PAGE = 1;
    private static final int ITEMS_PER_PAGE = 7;
    private static final long AMOUNT_PAGES = 3L;
    private static final String ALL_APPLICATIONS_COMMAND = "allApplications";
    private static final String INVALID_AMOUNT_PAGES = "6";
    private AllApplicationsCommand allApplicationsCommand;
    private ApplicationService applicationServiceImpl;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeMethod
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        applicationServiceImpl = mock(ApplicationServiceImpl.class);
        allApplicationsCommand = new AllApplicationsCommand(applicationServiceImpl);
    }

    @Test
    public void testExecuteShouldReturnForwardAllApplicationsWhenDefaultPage() throws ServicesException {
        when(request.getParameter(PARAMETER_PAGE)).thenReturn(null);
        when(request.getParameter(PARAMETER_COMMAND)).thenReturn(ALL_APPLICATIONS_COMMAND);
        when(applicationServiceImpl.findAmountPages(ITEMS_PER_PAGE)).thenReturn(AMOUNT_PAGES);
        when(applicationServiceImpl.findPageApplications(ITEMS_PER_PAGE, DEFAULT_PAGE)).thenReturn(new ArrayList<>());

        CommandResult expected = CommandResult.forward(ALL_APPLICATIONS_PAGE);
        CommandResult actual = allApplicationsCommand.execute(request, response);

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testExecuteShouldReturnForwardAllApplicationsWhenPageValid() throws ServicesException {
        when(request.getParameter(PARAMETER_PAGE)).thenReturn("3");
        when(request.getParameter(PARAMETER_COMMAND)).thenReturn(ALL_APPLICATIONS_COMMAND);
        when(applicationServiceImpl.findAmountPages(ITEMS_PER_PAGE)).thenReturn(AMOUNT_PAGES);
        when(applicationServiceImpl.findPageApplications(ITEMS_PER_PAGE, DEFAULT_PAGE)).thenReturn(new ArrayList<>());

        CommandResult expected = CommandResult.forward(ALL_APPLICATIONS_PAGE);
        CommandResult actual = allApplicationsCommand.execute(request, response);

        Assert.assertEquals(expected, actual);
    }

    @Test(expectedExceptions = ServicesException.class)
    public void testExecuteShouldThrowExceptionWhenPageNotValid() throws ServicesException {
        when(request.getParameter(PARAMETER_PAGE)).thenReturn(INVALID_AMOUNT_PAGES);
        when(request.getParameter(PARAMETER_COMMAND)).thenReturn(ALL_APPLICATIONS_COMMAND);
        when(applicationServiceImpl.findAmountPages(ITEMS_PER_PAGE)).thenReturn(AMOUNT_PAGES);
        when(applicationServiceImpl.findPageApplications(ITEMS_PER_PAGE, DEFAULT_PAGE)).thenReturn(new ArrayList<Application>());

        allApplicationsCommand.execute(request, response);
    }
}
