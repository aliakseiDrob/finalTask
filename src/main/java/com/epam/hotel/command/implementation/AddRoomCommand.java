package com.epam.hotel.command.implementation;

import com.epam.hotel.command.Command;
import com.epam.hotel.command.CommandResult;
import com.epam.hotel.entity.Room;
import com.epam.hotel.entity.enums.RoomStatus;
import com.epam.hotel.entity.enums.RoomType;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.api.RoomService;
import com.epam.hotel.logic.validators.api.RoomValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class AddRoomCommand implements Command {
    private static final String ROOM_ID = "roomId";
    private static final String ROOM_TYPE = "roomType";
    private static final String ROOM_PRICE = "price";
    private static final String ROOM_STATUS = "status";
    private static final String ROOM_CAPACITY = "capacity";
    private static final String ROOM_NUMBER = "number";
    private static final String ERROR_MESSAGE_PARAM = "errorMessage";
    private static final String ERROR_MESSAGE_VALUE = "wrongParameters";
    private static final String ALL_ROOMS_COMMAND = "controller?command=allRooms";
    private static final String ADD_ROOM_PAGE = "WEB-INF/view/addRoom.jsp";
    private final RoomService roomService;
    private final RoomValidator roomValidator;

    public AddRoomCommand(RoomService roomService, RoomValidator roomValidator) {
        this.roomService = roomService;
        this.roomValidator = roomValidator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServicesException {
        String id = request.getParameter(ROOM_ID);
        String type = request.getParameter(ROOM_TYPE);
        String price = request.getParameter(ROOM_PRICE);
        String status = request.getParameter(ROOM_STATUS);
        String capacity = request.getParameter(ROOM_CAPACITY);
        String number = request.getParameter(ROOM_NUMBER);
        if (!isRoomValid(type, price, capacity, number)) {
            request.setAttribute(ERROR_MESSAGE_PARAM, ERROR_MESSAGE_VALUE);
            return CommandResult.forward(ADD_ROOM_PAGE);
        }

        Room room = createRoom(id, type, price, status, capacity, number);
        roomService.addRoom(room);
        return CommandResult.redirect(ALL_ROOMS_COMMAND);
    }

    private Room createRoom(String id, String type, String price, String status, String capacity, String number) {
        Long roomId = Long.parseLong(id);
        RoomType roomType = RoomType.valueOf(type);
        BigDecimal roomPrice = new BigDecimal(price);
        RoomStatus roomStatus = RoomStatus.valueOf(status);
        byte roomCapacity = Byte.parseByte(capacity);
        int roomNumber = Integer.parseInt(number);
        return new Room(roomId, roomType, roomPrice, roomStatus, roomCapacity, roomNumber);
    }

    private boolean isRoomValid(String type, String price, String capacity, String number) {
        boolean isValidType = roomValidator.isValidRoomType(type);
        boolean isValidPrice = roomValidator.isValidPrice(price);
        boolean isValidCapacity = roomValidator.isValidCapacity(capacity);
        boolean isValidNumber = roomValidator.isValidRoomNumber(number);
        return isValidType && isValidPrice && isValidCapacity && isValidNumber;
    }

}
