package com.epam.hotel.command.implementation;

import com.epam.hotel.command.Command;
import com.epam.hotel.command.CommandResult;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.api.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserCommand implements Command {
    private static final String ALL_USERS_COMMAND = "controller?command=allUsers";
    private static final String DELETED_USER_ID = "deletedUserId";
    private final UserService userService;

    public DeleteUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServicesException {
        String idString = request.getParameter(DELETED_USER_ID);
        Long userId = Long.parseLong(idString);
        userService.deleteUser(userId);
        return CommandResult.redirect(ALL_USERS_COMMAND);
    }
}
