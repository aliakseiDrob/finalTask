package com.epam.hotel.command.implementation;

import com.epam.hotel.command.CommandResult;
import com.epam.hotel.entity.dto.ApplicationDto;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.ApplicationDtoServiceImpl;
import com.epam.hotel.logic.service.api.ApplicationDtoService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class AllInvoicesCommandTest {

    private static final String PARAMETER_PAGE = "page";
    private static final String PARAMETER_COMMAND = "command";
    private static final String PARAMETER_AMOUNT_PAGES = "amountPages";
    private static final String PARAMETER_COUNTER = "counter";
    private static final String ALL_INVOICES_PAGE = "WEB-INF/view/allInvoices.jsp";
    private static final String ALL_INVOICES_COMMAND = "allInvoices";
    private static final int DEFAULT_PAGE = 1;
    private static final int ITEMS_PER_PAGE = 7;
    private static final long AMOUNT_PAGES = 3L;
    private static final String INVALID_PAGE_NUMBER = "6";
    private static final String VALID_PAGE_NUMBER = "3";
    private static final String NULL_VALUE = null;
    private static final String PARAMETER_ALL_INVOICES = "allInvoices";
    private static final ApplicationDto APPLICATION_DTO_NULL = null;
    private static final int VALUE_COUNTER_FOR_DEFAULT_PAGE = 0;
    private static final int VALUE_COUNTER_FOR_PAGE = 14;

    private final ApplicationDtoService applicationDtoService = mock(ApplicationDtoServiceImpl.class);
    private final AllInvoicesCommand allInvoicesCommand = new AllInvoicesCommand(applicationDtoService);

    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeMethod
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    public void testExecuteShouldReturnForwardAllInvoicesWhenDefaultPage() throws ServicesException {
        List<ApplicationDto> applicationDtoList = Arrays.asList(APPLICATION_DTO_NULL, APPLICATION_DTO_NULL);
        when(request.getParameter(PARAMETER_PAGE)).thenReturn(NULL_VALUE);
        when(request.getParameter(PARAMETER_COMMAND)).thenReturn(ALL_INVOICES_COMMAND);
        when(applicationDtoService.findAmountPages(ITEMS_PER_PAGE)).thenReturn(AMOUNT_PAGES);
        when(applicationDtoService.findPaginatePageInvoices(ITEMS_PER_PAGE, DEFAULT_PAGE)).thenReturn(applicationDtoList);

        CommandResult expected = CommandResult.forward(ALL_INVOICES_PAGE);
        CommandResult actual = allInvoicesCommand.execute(request, response);

        verify(request, times(1)).setAttribute(PARAMETER_COUNTER, VALUE_COUNTER_FOR_DEFAULT_PAGE);
        verify(request, times(1)).setAttribute(PARAMETER_AMOUNT_PAGES, AMOUNT_PAGES);
        verify(request, times(1)).setAttribute(PARAMETER_COMMAND, ALL_INVOICES_COMMAND);
        verify(request, times(1)).setAttribute(PARAMETER_ALL_INVOICES, applicationDtoList);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testExecuteShouldReturnForwardAllInvoicesWhenPageValid() throws ServicesException {
        List<ApplicationDto> applicationDtoList = Arrays.asList(APPLICATION_DTO_NULL, APPLICATION_DTO_NULL);
        when(request.getParameter(PARAMETER_PAGE)).thenReturn(VALID_PAGE_NUMBER);
        when(request.getParameter(PARAMETER_COMMAND)).thenReturn(ALL_INVOICES_COMMAND);
        when(applicationDtoService.findAmountPages(ITEMS_PER_PAGE)).thenReturn(AMOUNT_PAGES);
        when(applicationDtoService.findPaginatePageInvoices(ITEMS_PER_PAGE, DEFAULT_PAGE)).thenReturn(applicationDtoList);

        CommandResult expected = CommandResult.forward(ALL_INVOICES_PAGE);
        CommandResult actual = allInvoicesCommand.execute(request, response);

        verify(request, times(1)).setAttribute(PARAMETER_COUNTER, VALUE_COUNTER_FOR_PAGE);
        verify(request, times(1)).setAttribute(PARAMETER_AMOUNT_PAGES, AMOUNT_PAGES);
        verify(request, times(1)).setAttribute(PARAMETER_COMMAND, ALL_INVOICES_COMMAND);
      //  verify(request, times(1)).setAttribute(PARAMETER_ALL_INVOICES, applicationDtoList);
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ServicesException.class)
    public void testExecuteShouldThrowExceptionWhenPageNotValid() throws ServicesException {
        when(request.getParameter(PARAMETER_PAGE)).thenReturn(INVALID_PAGE_NUMBER);
        when(request.getParameter(PARAMETER_COMMAND)).thenReturn(ALL_INVOICES_COMMAND);
        when(applicationDtoService.findAmountPages(ITEMS_PER_PAGE)).thenReturn(AMOUNT_PAGES);
        when(applicationDtoService.findPaginatePageInvoices(ITEMS_PER_PAGE, DEFAULT_PAGE)).thenReturn(new ArrayList<>());

        allInvoicesCommand.execute(request, response);
        verify(request, times(0)).setAttribute(anyString(), any());
    }
}
