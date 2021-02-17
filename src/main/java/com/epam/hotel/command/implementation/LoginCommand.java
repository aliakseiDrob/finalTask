package com.epam.hotel.command.implementation;

import com.epam.hotel.command.Command;
import com.epam.hotel.command.CommandResult;
import com.epam.hotel.entity.User;
import com.epam.hotel.entity.enums.UserRole;
import com.epam.hotel.entity.enums.UserStatus;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.api.UserService;
import com.epam.hotel.logic.validators.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {
    private static final String USER_ID = "userId";
    private static final String USER_ROLE = "userRole";
    private static final String USER_LOGIN = "login";
    private static final String USER_PASSWORD = "password";
    private static final String BOOKING_PAGE = "controller?command=bookingPage";
    private static final String IN_PROGRESS_APPLICATION_COMMAND = "controller?command=inProgressApplication";
    private static final String LOGIN_PAGE = "WEB-INF/view/login.jsp";
    private static final String ERROR_MESSAGE_PARAM = "errorMessage";
    private static final String ERROR_MESSAGE_WRONG_LOGIN_PASSWORD = "wrongLogin";
    private static final String ERROR_MESSAGE_BLOCKED_USER = "userBlocked";
    private static final String ROLE_ADMINISTRATOR = "ADMINISTRATOR";
    private static final String STATUS_DELETED = "DELETED";
    private static final String STATUS_BLOCKED = "BLOCKED";

    private final UserService userService;
    private final UserValidator userValidator;

    public LoginCommand(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServicesException {
        String login = request.getParameter(USER_LOGIN);
        String password = request.getParameter(USER_PASSWORD);
        if (!userValidator.isValid(login, password)) {
            request.setAttribute(ERROR_MESSAGE_PARAM, ERROR_MESSAGE_WRONG_LOGIN_PASSWORD);
            return CommandResult.forward(LOGIN_PAGE);
        }
        Optional<User> optionalUser = userService.login(login, password);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Long userId = user.getId();
            UserRole role = user.getRole();
            UserStatus userStatus = user.getStatus();
            String status = userStatus.toString();

            if (status.equals(STATUS_DELETED)) {
                request.setAttribute(ERROR_MESSAGE_PARAM, ERROR_MESSAGE_WRONG_LOGIN_PASSWORD);
                return CommandResult.forward(LOGIN_PAGE);
            }
            if (status.equals(STATUS_BLOCKED)) {
                request.setAttribute(ERROR_MESSAGE_PARAM, ERROR_MESSAGE_BLOCKED_USER);
                return CommandResult.forward(LOGIN_PAGE);
            }
            HttpSession session = request.getSession();
            session.setAttribute(USER_ROLE, role);
            session.setAttribute(USER_ID, userId);
            if (role.toString().equals(ROLE_ADMINISTRATOR)) {
                return CommandResult.forward(IN_PROGRESS_APPLICATION_COMMAND);
            } else {
                return CommandResult.forward(BOOKING_PAGE);
            }
        } else {
            request.setAttribute(ERROR_MESSAGE_PARAM, ERROR_MESSAGE_WRONG_LOGIN_PASSWORD);
            return CommandResult.forward(LOGIN_PAGE);
        }
    }
}