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
import static org.mockito.Mockito.times;

public class BlockUnblockUserCommandTest {
    private static final String ALL_USERS_COMMAND = "controller?command=allUsers";
    private static final String USER_ID = "userId";
    private static final String VALID_USER_ID = "1";
    private static final String INVALID_USER_ID = "0";
    private HttpServletRequest request;
    private HttpServletResponse response;
    private final UserService userService = mock(UserService.class);
    private final BlockUnblockUserCommand blockUnblockUserCommand = new BlockUnblockUserCommand(userService);
    @BeforeMethod
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }
    @Test
    public void testExecuteShouldReturnRedirectAllUsersCommand() throws ServicesException {
        when(request.getParameter(USER_ID)).thenReturn(VALID_USER_ID);
        CommandResult expected = CommandResult.redirect(ALL_USERS_COMMAND);
        CommandResult actual = blockUnblockUserCommand.execute(request,response);
        verify(userService,times(1)).blockUnblockUser(anyLong());
        Assert.assertEquals(actual,expected);
    }
    @Test(expectedExceptions = ServicesException.class)
    public void testExecuteShouldThrowServicesExceptionWhenRoomNotExist() throws ServicesException {
        when(request.getParameter(USER_ID)).thenReturn(INVALID_USER_ID);
        doThrow(new ServicesException()).when(userService).blockUnblockUser(anyLong());
        blockUnblockUserCommand.execute(request,response);
        verify(userService,times(1)).blockUnblockUser(anyLong());
    }
}