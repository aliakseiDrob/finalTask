package com.epam.hotel.command.implementation;

import com.epam.hotel.command.CommandResult;
import com.epam.hotel.exception.ServicesException;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

public class LogOutCommandTest {
    private static final String MAIN_PAGE = "WEB-INF/view/main.jsp";

    private final LogOutCommand logOutCommand = new LogOutCommand();
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final HttpSession session = mock(HttpSession.class);

    @Test
    public void testExecuteShouldReturnMainPageWhenUserLogOut() throws ServicesException {
        when(request.getSession()).thenReturn(session);

        CommandResult expected = CommandResult.forward(MAIN_PAGE);
        CommandResult actual = logOutCommand.execute(request,response);

        verify(session,times(1)).invalidate();
        Assert.assertEquals(actual,expected);

    }
}