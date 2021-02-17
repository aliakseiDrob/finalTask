package com.epam.hotel.command.implementation;

import com.epam.hotel.command.CommandResult;
import com.epam.hotel.entity.Application;
import com.epam.hotel.entity.enums.RoomType;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.api.ApplicationService;
import com.epam.hotel.logic.validators.api.ApplicationValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

public class BookingCommandTest {
    private static final String APPLICATION_HISTORY = "controller?command=applicationHistory";
    private static final String DATE_CHECK_IN = "dateCheckIn";
    private static final String DATE_CHECK_OUT = "dateCheckOut";
    private static final String ROOM_TYPE = "roomType";
    private static final String CAPACITY = "capacity";
    private static final String USER_ID = "userId";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String INCORRECT_DATA_MESSAGE = "incorrectData";
    private static final String BOOKING_PAGE = "WEB-INF/view/bookingPage.jsp";
    private static final long USER_ID_VALUE = 1L;
    private static final String VALID_ROOM_CAPACITY = "4";
    private static final String VALID_ROOM_TYPE = RoomType.COMFORT.toString();
    private static final String DATE_CHECK_OUT_VALUE = LocalDate.now().toString();
    private static final String DATE_CHECK_IN_VALUE = LocalDate.now().toString();

    private final HttpSession session = mock(HttpSession.class);
    private HttpServletRequest request;
    private HttpServletResponse response;
    private final ApplicationService applicationService = mock(ApplicationService.class);
    private final ApplicationValidator applicationValidator = mock(ApplicationValidator.class);
    private final BookingCommand bookingCommand = new BookingCommand(applicationService, applicationValidator);

    @BeforeMethod
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(USER_ID)).thenReturn(USER_ID_VALUE);
        when(request.getParameter(DATE_CHECK_IN)).thenReturn(DATE_CHECK_IN_VALUE);
        when(request.getParameter(DATE_CHECK_OUT)).thenReturn(DATE_CHECK_OUT_VALUE);
        when(request.getParameter(ROOM_TYPE)).thenReturn(VALID_ROOM_TYPE);
        when(request.getParameter(CAPACITY)).thenReturn(VALID_ROOM_CAPACITY);
        when(applicationValidator.isValidDateCheckIn(anyString())).thenReturn(true);
        when(applicationValidator.isValidDateCheckOut(anyString(), anyString())).thenReturn(true);
        when(applicationValidator.isValidCapacity(anyString())).thenReturn(true);
    }

    @Test
    public void testExecuteShouldReturnRedirectWhenDataValid() throws ServicesException {
        when(applicationValidator.isValidRoomType(anyString())).thenReturn(true);

        CommandResult expected = CommandResult.redirect(APPLICATION_HISTORY);
        CommandResult actual = bookingCommand.execute(request, response);

        verify(request, times(0)).setAttribute(anyString(), any());
        verify(applicationService, times(1)).saveApplication(any(Application.class));
        Assert.assertEquals(actual, expected);

    }

    @Test
    public void testExecuteShouldReturnForwardWhenDataNotValid() throws ServicesException {
        when(applicationValidator.isValidRoomType(anyString())).thenReturn(false);

        CommandResult expected = CommandResult.forward(BOOKING_PAGE);
        CommandResult actual = bookingCommand.execute(request, response);

        verify(request, times(1)).setAttribute(ERROR_MESSAGE, INCORRECT_DATA_MESSAGE);
        verify(applicationService, times(0)).saveApplication(any(Application.class));
        Assert.assertEquals(actual, expected);
    }
}