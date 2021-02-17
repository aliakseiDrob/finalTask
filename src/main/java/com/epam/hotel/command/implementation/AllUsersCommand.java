package com.epam.hotel.command.implementation;

import com.epam.hotel.command.Command;
import com.epam.hotel.command.CommandResult;
import com.epam.hotel.entity.dto.UserDto;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.api.UserDtoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AllUsersCommand implements Command {
    private static final String ALL_USERS_PAGE = "WEB-INF/view/allUsers.jsp";
    private static final String ALL_USERS = "allUsers";
    private static final int DEFAULT_PAGE = 1;
    private static final int ITEMS_PER_PAGE = 7;
    private static final String PARAMETER_PAGE = "page";
    private static final String PARAMETER_COMMAND = "command";
    private static final String COUNTER = "counter";
    private static final String AMOUNT_PAGES = "amountPages";
    private final UserDtoService userDtoService;

    public AllUsersCommand(UserDtoService userDtoService) {
        this.userDtoService = userDtoService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServicesException {
        String pageNumber = request.getParameter(PARAMETER_PAGE);
        String command = request.getParameter(PARAMETER_COMMAND);
        int page = pageNumber == null ? DEFAULT_PAGE : Integer.parseInt(pageNumber);
        long amountPages = userDtoService.findAmountPages(ITEMS_PER_PAGE);
        if (page > amountPages) {
            throw new ServicesException("page " + page + " doesn't exist");
        }
        List<UserDto> userList = userDtoService.findPageUsers(ITEMS_PER_PAGE, page);
        int counter = (page - 1) * ITEMS_PER_PAGE;
        request.setAttribute(COUNTER, counter);
        request.setAttribute(AMOUNT_PAGES, amountPages);
        request.setAttribute(PARAMETER_COMMAND, command);
        request.setAttribute(ALL_USERS, userList);
        return CommandResult.forward(ALL_USERS_PAGE);
    }
}
