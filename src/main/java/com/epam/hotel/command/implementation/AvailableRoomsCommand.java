package com.epam.hotel.command.implementation;

import com.epam.hotel.command.Command;
import com.epam.hotel.command.CommandResult;
import com.epam.hotel.entity.Application;
import com.epam.hotel.entity.Room;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.api.ApplicationService;
import com.epam.hotel.logic.service.api.RoomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;

public class AvailableRoomsCommand implements Command {
    private static final String CURRENT_APPLICATION = "application";
    private static final String ALL_ROOMS = "allRooms";
    private static final String APPLICATION_ID = "applicationId";
    private static final String ADD_INVOICE_PAGE = "WEB-INF/view/addInvoice.jsp";
    private final ApplicationService applicationService;
    private final RoomService roomService;

    public AvailableRoomsCommand(ApplicationService applicationService, RoomService roomService) {
        this.applicationService = applicationService;
        this.roomService = roomService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServicesException {
        String idString = request.getParameter(APPLICATION_ID);
        Long id = Long.parseLong(idString);
        Application application = applicationService.findById(id);
        LocalDate check_in = application.getDateCheckIn();
        LocalDate check_out = application.getDateCheckOut();
        List<Room> roomList = roomService.getAllAvailableRooms(check_in, check_out);
        request.setAttribute(ALL_ROOMS, roomList);
        request.setAttribute(CURRENT_APPLICATION, application);
        return CommandResult.forward(ADD_INVOICE_PAGE);
    }
}
