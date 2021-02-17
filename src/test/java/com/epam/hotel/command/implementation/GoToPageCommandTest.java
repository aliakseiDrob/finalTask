package com.epam.hotel.command.implementation;

import com.epam.hotel.command.CommandResult;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.*;

public class GoToPageCommandTest {
    private static final String PAGE_JSP = "example.jsp";
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);

    @Test
    public void testExecute() {
        GoToPageCommand goToPageCommand = new GoToPageCommand(PAGE_JSP);
        CommandResult expected = CommandResult.forward(PAGE_JSP);
        CommandResult actual = goToPageCommand.execute(request,response);
        Assert.assertEquals(actual,expected);
    }
}