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

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginCommandTest {

    private static final String USER_LOGIN = "login";
    private static final String USER_PASSWORD = "password";
    private static final String IN_PROGRESS_APPLICATION_COMMAND = "controller?command=inProgressApplication";
    private static final String LOGIN_PAGE = "WEB-INF/view/login.jsp";
    private static final String ERROR_MESSAGE_PARAM = "errorMessage";
    private static final String ERROR_MESSAGE_WRONG_LOGIN_PASSWORD = "wrongLogin";
    private final User USER = new User(1L,"login","password",UserRole.ADMINISTRATOR,UserStatus.ACTIVE);
    private final User BLOCKED_USER = new User(1L,"login","password",UserRole.GUEST,UserStatus.BLOCKED);
    private final User ADMIN_USER = new User(1L,"login","password",UserRole.ADMINISTRATOR,UserStatus.ACTIVE);
    private UserService userService;
    private UserValidator userValidator;
    HttpServletRequest request;
    HttpServletResponse response;

    @BeforeMethod
    public void createLoginServiceAndValidator() {
        userService = mock(UserServiceImpl.class);
        userValidator = mock(UserValidator.class);
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }
    @Test
    public void testExecuteShouldForwardToLoginPageWhenDataInvalid() throws ServicesException{
        LoginCommand loginCommand = new LoginCommand(userService,userValidator);
        CommandResult expected = CommandResult.forward(LOGIN_PAGE);
        when(request.getParameter(anyString())).thenReturn("anyString");
        when(request.getParameter(anyString())).thenReturn("anyString");
        when(userValidator.isValid("anyString","anyString")).thenReturn(false);
        CommandResult actual = loginCommand.execute(request,response);
        Assert.assertEquals(expected,actual);
    }
//    @Test
//    public void testExecuteShouldForwardToLoginPageWhenUserBlocked() throws ServicesException{
//        LoginCommand loginCommand = new LoginCommand(userService,userValidator);
//        CommandResult expected = CommandResult.forward(LOGIN_PAGE);
//        when(request.getParameter(anyString())).thenReturn("anyString");
//        when(request.getParameter(anyString())).thenReturn("anyString");
//        when(userValidator.isValid("anyString","anyString")).thenReturn(true);
//        when(userService.login("anyString","anyString")).thenReturn(Optional.of(BLOCKED_USER));
//        CommandResult actual = loginCommand.execute(request,response);
//        Assert.assertEquals(expected,actual);
//    }
//
//    @Test
//    public void testExecuteShouldForwardToAllApplicationsPageWhenDataValid() throws ServicesException{
//    LoginCommand loginCommand = new LoginCommand(userService,userValidator);
//        CommandResult expected = CommandResult.forward(IN_PROGRESS_APPLICATION_COMMAND);
//        when(request.getParameter(anyString())).thenReturn("anyString");
//        when(request.getParameter(anyString())).thenReturn("anyString");
//        when(userValidator.isValid("anyString","anyString")).thenReturn(true);
//        when(userService.login("login","password")).thenReturn(Optional.of(ADMIN_USER));
//        CommandResult actual = loginCommand.execute(request,response);
//        Assert.assertEquals(expected,actual);
//    }

}
