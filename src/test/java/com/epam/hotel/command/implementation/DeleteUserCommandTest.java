package com.epam.hotel.command.implementation;

import com.epam.hotel.command.CommandResult;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.api.UserService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class DeleteUserCommandTest {
    private static final String ALL_USERS_COMMAND = "controller?command=allUsers";
    private static final String DELETED_USER_ID = "deletedUserId";
    private static final String VALID_USER_ID = "1";
    private HttpServletRequest request;
    private HttpServletResponse response;
    private final UserService userService = mock(UserService.class);
    private final DeleteUserCommand deleteUserCommand = new DeleteUserCommand(userService);

    @BeforeMethod
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    public void testExecuteShouldReturnRedirectAllUserCommand() throws ServicesException {
        when(request.getParameter(DELETED_USER_ID)).thenReturn(VALID_USER_ID);
        CommandResult expected = CommandResult.redirect(ALL_USERS_COMMAND);
        CommandResult actual = deleteUserCommand.execute(request, response);

        verify(userService, times(1)).deleteUser(anyLong());
        Assert.assertEquals(actual, expected);

    }
}