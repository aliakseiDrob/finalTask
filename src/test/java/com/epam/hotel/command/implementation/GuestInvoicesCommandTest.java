package com.epam.hotel.command.implementation;

import com.epam.hotel.command.CommandResult;
import com.epam.hotel.entity.Application;
import com.epam.hotel.entity.dto.ApplicationDto;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.api.ApplicationDtoService;
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
import static org.mockito.Mockito.times;

public class GuestInvoicesCommandTest {
    private static final String ALL_INVOICES_PAGE = "WEB-INF/view/allInvoices.jsp";
    private static final String USER_ID = "userId";
    private static final String ALL_INVOICES = "allInvoices";
    private static final String PARAMETER_PAGE = "page";
    private static final String PARAMETER_COMMAND = "command";
    private static final String AMOUNT_PAGES = "amountPages";
    private static final String COMMAND_VALUE = "AnyCommand";
    private static final String VALID_PAGE_VALUE = "3";
    private static final String INVALID_PAGE_VALUE = "6";
    private static final long AMOUNT_PAGES_VALUE = 3L;
    private static final long USER_ID_VALUE = 1L;
    private static final String NULL_VALUE = null;
    private final ApplicationDtoService applicationDtoService = mock(ApplicationDtoService.class);
    private final GuestInvoicesCommand guestInvoicesCommand = new GuestInvoicesCommand(applicationDtoService);
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @BeforeMethod
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(USER_ID)).thenReturn(USER_ID_VALUE);
        when(request.getParameter(PARAMETER_COMMAND)).thenReturn(COMMAND_VALUE);
    }

    @Test(expectedExceptions = ServicesException.class)
    public void testExecuteShouldThrowExceptionWhenPageNotExist() throws ServicesException {
        when(request.getParameter(PARAMETER_PAGE)).thenReturn(INVALID_PAGE_VALUE);
        when(applicationDtoService.findAmountPagesByUserId(anyLong(), anyInt())).thenReturn(AMOUNT_PAGES_VALUE);
        guestInvoicesCommand.execute(request, response);
    }

    @Test
    public void testExecuteShouldReturnForwardAllInvoicesPageWhenDefaultPage() throws ServicesException {
        List<ApplicationDto> applicationDtoList = Arrays.asList(null, null);
        when(request.getParameter(PARAMETER_PAGE)).thenReturn(NULL_VALUE);
        when(applicationDtoService.findAmountPagesByUserId(anyLong(), anyInt())).thenReturn(AMOUNT_PAGES_VALUE);
        when(applicationDtoService.findPaginatePageByUserId(anyLong(), anyInt(), anyInt())).thenReturn(applicationDtoList);

        CommandResult expected = CommandResult.forward(ALL_INVOICES_PAGE);
        CommandResult actual = guestInvoicesCommand.execute(request, response);

        verify(request, times(1)).setAttribute(AMOUNT_PAGES, AMOUNT_PAGES_VALUE);
        verify(request, times(1)).setAttribute(PARAMETER_COMMAND, COMMAND_VALUE);
       verify(request, times(1)).setAttribute(ALL_INVOICES, applicationDtoList);
        Assert.assertEquals(actual, expected);
    }
    @Test
    public void testExecuteShouldReturnForwardAllInvoicesPageWhenPageValid() throws ServicesException {
        List<ApplicationDto> applicationDtoList = Arrays.asList(null, null);
        when(request.getParameter(PARAMETER_PAGE)).thenReturn(VALID_PAGE_VALUE);
        when(applicationDtoService.findAmountPagesByUserId(anyLong(), anyInt())).thenReturn(AMOUNT_PAGES_VALUE);
        when(applicationDtoService.findPaginatePageByUserId(anyLong(), anyInt(), anyInt())).thenReturn(applicationDtoList);

        CommandResult expected = CommandResult.forward(ALL_INVOICES_PAGE);
        CommandResult actual = guestInvoicesCommand.execute(request, response);

        verify(request, times(1)).setAttribute(AMOUNT_PAGES, AMOUNT_PAGES_VALUE);
        verify(request, times(1)).setAttribute(PARAMETER_COMMAND, COMMAND_VALUE);
        verify(request, times(1)).setAttribute(ALL_INVOICES, applicationDtoList);
        Assert.assertEquals(actual, expected);
    }
}