package com.epam.hotel.command.implementation;

import com.epam.hotel.command.CommandResult;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.ApplicationDtoServiceImpl;
import com.epam.hotel.logic.service.api.ApplicationDtoService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AllInvoicesCommandTest {

    private static final String PARAMETER_PAGE = "page";
    private static final String PARAMETER_COMMAND = "command";
    private static final String ALL_INVOICES_PAGE = "WEB-INF/view/allInvoices.jsp";
    private static final int DEFAULT_PAGE = 1;
    private static final int ITEMS_PER_PAGE = 7;
    private static final long AMOUNT_PAGES = 3L;
    private static final String ALL_INVOICES_COMMAND = "allInvoices";
    private static final String INVALID_PAGE_NUMBER = "6";
    private static final String VALID_PAGE_NUMBER = "3";
    private AllInvoicesCommand allInvoicesCommand;
    private ApplicationDtoService applicationDtoService;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeMethod
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        applicationDtoService = mock(ApplicationDtoServiceImpl.class);
        allInvoicesCommand = new AllInvoicesCommand(applicationDtoService);
    }

    @Test
    public void testExecuteShouldReturnForwardAllInvoicesWhenDefaultPage() throws ServicesException {
        when(request.getParameter(PARAMETER_PAGE)).thenReturn(null);
        when(request.getParameter(PARAMETER_COMMAND)).thenReturn(ALL_INVOICES_COMMAND);
        when(applicationDtoService.findAmountPages(ITEMS_PER_PAGE)).thenReturn(AMOUNT_PAGES);
        when(applicationDtoService.findPageInvoices(ITEMS_PER_PAGE, DEFAULT_PAGE)).thenReturn(new ArrayList<>());

        CommandResult expected = CommandResult.forward(ALL_INVOICES_PAGE);
        CommandResult actual = allInvoicesCommand.execute(request, response);

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testExecuteShouldReturnForwardAllInvoicesWhenPageValid() throws ServicesException {
        when(request.getParameter(PARAMETER_PAGE)).thenReturn(VALID_PAGE_NUMBER);
        when(request.getParameter(PARAMETER_COMMAND)).thenReturn(ALL_INVOICES_COMMAND);
        when(applicationDtoService.findAmountPages(ITEMS_PER_PAGE)).thenReturn(AMOUNT_PAGES);
        when(applicationDtoService.findPageInvoices(ITEMS_PER_PAGE, DEFAULT_PAGE)).thenReturn(new ArrayList<>());

        CommandResult expected = CommandResult.forward(ALL_INVOICES_PAGE);
        CommandResult actual = allInvoicesCommand.execute(request, response);

        Assert.assertEquals(expected, actual);
    }

    @Test(expectedExceptions = ServicesException.class)
    public void testExecuteShouldThrowExceptionWhenPageNotValid() throws ServicesException {
        when(request.getParameter(PARAMETER_PAGE)).thenReturn(INVALID_PAGE_NUMBER);
        when(request.getParameter(PARAMETER_COMMAND)).thenReturn(ALL_INVOICES_COMMAND);
        when(applicationDtoService.findAmountPages(ITEMS_PER_PAGE)).thenReturn(AMOUNT_PAGES);
        when(applicationDtoService.findPageInvoices(ITEMS_PER_PAGE, DEFAULT_PAGE)).thenReturn(new ArrayList<>());

        allInvoicesCommand.execute(request, response);
    }
}
