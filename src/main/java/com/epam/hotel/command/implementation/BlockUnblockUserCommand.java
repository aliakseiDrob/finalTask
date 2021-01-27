package com.epam.hotel.command.implementation;

import com.epam.hotel.command.Command;
import com.epam.hotel.command.CommandResult;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.api.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockUnblockUserCommand implements Command {
    private static final String ALL_USERS_COMMAND = "controller?command=allUsers";
    private static final String USER_ID = "userId";
    private static final String PARAMETER_PAGE = "page";
    private final UserService userService;

    public BlockUnblockUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServicesException {
        String page = request.getParameter(PARAMETER_PAGE);
        String idToString = request.getParameter(USER_ID);
        Long userId = Long.parseLong(idToString);
        userService.blockUnblockUser(userId);
        request.setAttribute(PARAMETER_PAGE, page);
        return CommandResult.redirect(ALL_USERS_COMMAND);
    }
}
