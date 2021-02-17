package com.epam.hotel.command.implementation;

import com.epam.hotel.command.Command;
import com.epam.hotel.command.CommandResult;
import com.epam.hotel.entity.Application;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.ApplicationServiceImpl;
import com.epam.hotel.logic.service.api.ApplicationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class InProgressApplicationsCommand implements Command {
    private static final String ALL_APPLICATIONS_PAGE = "WEB-INF/view/allApplications.jsp";
    private static final String ALL_APPLICATIONS = "allApplications";
    private static final int DEFAULT_PAGE = 1;
    private static final int ITEMS_PER_PAGE = 7;
    private static final String PARAMETER_PAGE = "page";
    private static final String PARAMETER_COMMAND = "command";
    private static final String AMOUNT_PAGES = "amountPages";
    private final ApplicationService applicationService;

    public InProgressApplicationsCommand(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServicesException {
        String pageNumber = request.getParameter(PARAMETER_PAGE);
        String command = request.getParameter(PARAMETER_COMMAND);
        int page = pageNumber == null ? DEFAULT_PAGE : Integer.parseInt(pageNumber);
        long amountPages = applicationService.findAmountPagesInProgress(ITEMS_PER_PAGE);
        if (page > amountPages) {
            throw new ServicesException("page " + page + " doesn't exist");
        }
        List<Application> applicationList = applicationService.findPageApplicationsInProgress(ITEMS_PER_PAGE, page);

        request.setAttribute(AMOUNT_PAGES, amountPages);
        request.setAttribute(PARAMETER_COMMAND, command);
        request.setAttribute(ALL_APPLICATIONS, applicationList);
        return CommandResult.forward(ALL_APPLICATIONS_PAGE);

    }
}
