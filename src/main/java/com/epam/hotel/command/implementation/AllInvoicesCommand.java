package com.epam.hotel.command.implementation;

import com.epam.hotel.command.Command;
import com.epam.hotel.command.CommandResult;
import com.epam.hotel.entity.dto.ApplicationDto;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.api.ApplicationDtoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AllInvoicesCommand implements Command {
    private static final String ALL_INVOICES_PAGE = "WEB-INF/view/allInvoices.jsp";
    private static final String ALL_INVOICES = "allInvoices";
    private static final int DEFAULT_PAGE = 1;
    private static final int ITEMS_PER_PAGE = 7;
    private static final String PARAMETER_PAGE = "page";
    private static final String PARAMETER_COMMAND = "command";
    private static final String COUNTER = "counter";
    private static final String AMOUNT_PAGES = "amountPages";
    private final ApplicationDtoService applicationDtoService;

    public AllInvoicesCommand(ApplicationDtoService applicationDtoService) {
        this.applicationDtoService = applicationDtoService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServicesException {
        String pageNumber = request.getParameter(PARAMETER_PAGE);
        String command = request.getParameter(PARAMETER_COMMAND);
        int page = pageNumber == null ? DEFAULT_PAGE : Integer.parseInt(pageNumber);
        long amountPages = applicationDtoService.findAmountPages(ITEMS_PER_PAGE);
        if (page > amountPages) {
            throw new ServicesException("page " + page + " doesn't exist");
        }
        List<ApplicationDto> applicationDtoList = applicationDtoService.findPageInvoices(ITEMS_PER_PAGE, page);
        int counter = (page - 1) * ITEMS_PER_PAGE;
        request.setAttribute(COUNTER, counter);
        request.setAttribute(AMOUNT_PAGES, amountPages);
        request.setAttribute(PARAMETER_COMMAND, command);
        request.setAttribute(ALL_INVOICES, applicationDtoList);
        return CommandResult.forward(ALL_INVOICES_PAGE);
    }
}
