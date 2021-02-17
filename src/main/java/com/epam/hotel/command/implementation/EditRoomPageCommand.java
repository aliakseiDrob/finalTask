package com.epam.hotel.command.implementation;

import com.epam.hotel.command.Command;
import com.epam.hotel.command.CommandResult;
import com.epam.hotel.entity.Room;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.api.RoomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditRoomPageCommand implements Command {
    private static final String PARAMETER_ROOM_ID = "roomId";
    private static final String PARAMETER_ROOM = "room";
    private static final String EDIT_ROOM_PAGE = "WEB-INF/view/editRoom.jsp";
    private final RoomService roomService;

    public EditRoomPageCommand(RoomService roomService) {
        this.roomService = roomService;

    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServicesException {
        String roomId = request.getParameter(PARAMETER_ROOM_ID);
        Long id = Long.parseLong(roomId);
        Room room = roomService.findById(id);
        request.setAttribute(PARAMETER_ROOM, room);
        return CommandResult.forward(EDIT_ROOM_PAGE);
    }
}
