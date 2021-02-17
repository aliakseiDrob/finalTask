package com.epam.hotel.command.implementation;

import com.epam.hotel.command.CommandResult;
import com.epam.hotel.entity.User;
import com.epam.hotel.entity.enums.UserRole;
import com.epam.hotel.entity.enums.UserStatus;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.UserServiceImpl;
import com.epam.hotel.logic.service.api.UserService;
import com.epam.hotel.logic.validators.UserValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class LoginCommandTest {

    private static final String USER_LOGIN = "login";
    private static final String USER_PASSWORD = "password";
    private static final String USER_ID = "userId";
    private static final String USER_ROLE = "userRole";
    private static final String IN_PROGRESS_APPLICATION_COMMAND = "controller?command=inProgressApplication";
    private static final String BOOKING_PAGE = "controller?command=bookingPage";
    private static final String LOGIN_PAGE = "WEB-INF/view/login.jsp";
    private static final String ERROR_MESSAGE_PARAM = "errorMessage";
    private static final String ERROR_MESSAGE_WRONG_LOGIN_PASSWORD = "wrongLogin";
    private static final String ERROR_MESSAGE_BLOCKED_USER = "userBlocked";
    private final User BLOCKED_USER = new User(1L, "login", "password", UserRole.GUEST, UserStatus.BLOCKED);
    private final User DELETED_USER = new User(1L, "login", "password", UserRole.GUEST, UserStatus.DELETED);
    private final User ADMIN_USER = new User(1L, "login", "password", UserRole.ADMINISTRATOR, UserStatus.ACTIVE);
    private final User GUEST_USER = new User(1L, "login", "password", UserRole.GUEST, UserStatus.ACTIVE);
    private final UserService userService = mock(UserServiceImpl.class);
    private final UserValidator userValidator = mock(UserValidator.class);
    private final LoginCommand loginCommand = new LoginCommand(userService, userValidator);
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @BeforeMethod
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        when(request.getParameter(USER_LOGIN)).thenReturn("login");
        when(request.getParameter(USER_PASSWORD)).thenReturn("password");
    }

    @Test
    public void testExecuteShouldForwardToLoginPageWhenDataNotValid() throws ServicesException {
        when(userValidator.isValid(anyString(), anyString())).thenReturn(false);

        CommandResult expected = CommandResult.forward(LOGIN_PAGE);
        CommandResult actual = loginCommand.execute(request, response);

        verify(request, times(1)).setAttribute(ERROR_MESSAGE_PARAM, ERROR_MESSAGE_WRONG_LOGIN_PASSWORD);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testExecuteShouldForwardToLoginPageWhenUserDeleted() throws ServicesException {
        when(userValidator.isValid(anyString(), anyString())).thenReturn(true);
        when(userService.login("login", "password")).thenReturn(Optional.of(DELETED_USER));

        CommandResult expected = CommandResult.forward(LOGIN_PAGE);
        CommandResult actual = loginCommand.execute(request, response);

        verify(request, times(1)).setAttribute(ERROR_MESSAGE_PARAM, ERROR_MESSAGE_WRONG_LOGIN_PASSWORD);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testExecuteShouldForwardToLoginPageWhenUserBlocked() throws ServicesException {
        when(userValidator.isValid(anyString(), anyString())).thenReturn(true);
        when(userService.login("login", "password")).thenReturn(Optional.of(BLOCKED_USER));

        CommandResult expected = CommandResult.forward(LOGIN_PAGE);
        CommandResult actual = loginCommand.execute(request, response);

        verify(request, times(1)).setAttribute(ERROR_MESSAGE_PARAM, ERROR_MESSAGE_BLOCKED_USER);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testExecuteShouldForwardToApplicationPageWhenUserAdmin() throws ServicesException {
        when(request.getSession()).thenReturn(session);
        when(userValidator.isValid(anyString(), anyString())).thenReturn(true);
        when(userService.login("login", "password")).thenReturn(Optional.of(ADMIN_USER));


        CommandResult expected = CommandResult.forward(IN_PROGRESS_APPLICATION_COMMAND);
        CommandResult actual = loginCommand.execute(request, response);

        verify(session, times(1)).setAttribute(USER_ROLE, UserRole.ADMINISTRATOR);
        verify(session, times(1)).setAttribute(USER_ID, 1L);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testExecuteShouldForwardToBookingPageWhenUserGuest() throws ServicesException {
        when(request.getSession()).thenReturn(session);
        when(userValidator.isValid(anyString(), anyString())).thenReturn(true);
        when(userService.login("login", "password")).thenReturn(Optional.of(GUEST_USER));

        CommandResult expected = CommandResult.forward(BOOKING_PAGE);
        CommandResult actual = loginCommand.execute(request, response);

        verify(session, times(1)).setAttribute(USER_ROLE, UserRole.GUEST);
        verify(session, times(1)).setAttribute(USER_ID, 1L);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testExecuteShouldForwardToLoginPageWhenUserNotExist() throws ServicesException {
        when(request.getSession()).thenReturn(session);
        when(userValidator.isValid(anyString(), anyString())).thenReturn(true);
        when(userService.login("login", "password")).thenReturn(Optional.empty());

        CommandResult expected = CommandResult.forward(LOGIN_PAGE);
        CommandResult actual = loginCommand.execute(request, response);

        Assert.assertEquals(expected, actual);
    }


}
