package com.epam.hotel.command.implementation;

import com.epam.hotel.command.Command;
import com.epam.hotel.command.CommandResult;
import com.epam.hotel.entity.Application;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.api.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GuestApplicationsCommand implements Command {
    private static final String ALL_APPLICATION_PAGE = "WEB-INF/view/allApplications.jsp";
    private static final String USER_ID = "userId";
    private static final String ALL_APPLICATIONS = "allApplications";
    private static final int DEFAULT_PAGE = 1;
    private static final int ITEMS_PER_PAGE = 5;
    private static final String PARAMETER_PAGE = "page";
    private static final String PARAMETER_COMMAND = "command";
    private static final String AMOUNT_PAGES = "amountPages";
    private final ApplicationService applicationService;

    public GuestApplicationsCommand(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServicesException {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute(USER_ID);
        String pageNumber = request.getParameter(PARAMETER_PAGE);
        String command = request.getParameter(PARAMETER_COMMAND);
        int page;
        if (pageNumber == null) {
            page = DEFAULT_PAGE;
        } else {
            page = Integer.parseInt(pageNumber);
        }
        long amountPages = applicationService.findAmountByUserId(userId, ITEMS_PER_PAGE);
        if (page > amountPages) {
            throw new ServicesException("page " + page + " doesn't exist");
        }
        List<Application> applicationList = applicationService.findPageByUserId(userId, ITEMS_PER_PAGE, page);

        request.setAttribute(AMOUNT_PAGES, amountPages);
        request.setAttribute(PARAMETER_COMMAND, command);
        request.setAttribute(ALL_APPLICATIONS, applicationList);
        return CommandResult.forward(ALL_APPLICATION_PAGE);
    }
}
