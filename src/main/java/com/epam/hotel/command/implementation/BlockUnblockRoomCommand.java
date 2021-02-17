package com.epam.hotel.command.implementation;

import com.epam.hotel.command.Command;
import com.epam.hotel.command.CommandResult;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.api.RoomService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockUnblockRoomCommand implements Command {
    private static final String ALL_ROOMS_COMMAND = "controller?command=allRooms";
    private static final String PARAMETER_ROOM_ID = "roomId";
    private final RoomService roomService;

    public BlockUnblockRoomCommand(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServicesException {
        String idToString = request.getParameter(PARAMETER_ROOM_ID);
        Long roomId = Long.parseLong(idToString);
        roomService.blockUnblockRoom(roomId);
        return CommandResult.redirect(ALL_ROOMS_COMMAND);
    }
}
