package com.epam.hotel.command.implementation;

import com.epam.hotel.command.Command;
import com.epam.hotel.command.CommandResult;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.api.ApplicationRoomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddInvoiceCommand implements Command {
    private static final String COMMAND_IN_PROGRESS_APPLICATION = "controller?command=inProgressApplication";
    private static final String ROOM_ID = "roomId";
    private static final String APPLICATION_ID = "applicationId";
    private final ApplicationRoomService applicationRoomService;

    public AddInvoiceCommand(ApplicationRoomService applicationRoomService) {
        this.applicationRoomService = applicationRoomService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServicesException {
        String roomIdString = request.getParameter(ROOM_ID);
        String applicationIdString = request.getParameter(APPLICATION_ID);
        Long roomId = Long.parseLong(roomIdString);
        Long applicationId = Long.parseLong(applicationIdString);

        applicationRoomService.createInvoice(roomId, applicationId);
        return CommandResult.forward(COMMAND_IN_PROGRESS_APPLICATION);
    }
}
