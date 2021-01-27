package com.epam.hotel.command.implementation;

import com.epam.hotel.command.Command;
import com.epam.hotel.command.CommandResult;
import com.epam.hotel.entity.Application;
import com.epam.hotel.entity.enums.ApplicationStatus;
import com.epam.hotel.entity.enums.RoomType;
import com.epam.hotel.exception.ServicesException;
import com.epam.hotel.logic.service.api.ApplicationService;
import com.epam.hotel.logic.validators.api.ApplicationValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;

public class BookingCommand implements Command {
    private static final String APPLICATION_HISTORY = "controller?command=applicationHistory";
    private static final String APPLICATION_ID = "applicationId";
    private static final String DATE_CHECK_IN = "dateCheckIn";
    private static final String DATE_CHECK_OUT = "dateCheckOut";
    private static final String ROOM_TYPE = "roomType";
    private static final String CAPACITY = "capacity";
    private static final String STATUS = "status";
    private static final String USER_ID = "userId";
    private static final String ROOM_ID = "roomId";
    private static final String INVOICE = "invoice";
    private static final String PARSING_ERROR = "Parsing error";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String INCORRECT_DATA_MESSAGE = "incorrectData";
    private static final String BOOKING_PAGE = "WEB-INF/view/bookingPage.jsp";

    private final ApplicationService applicationService;
    private final ApplicationValidator applicationValidator;

    public BookingCommand(ApplicationService applicationService, ApplicationValidator applicationValidator) {
        this.applicationService = applicationService;
        this.applicationValidator = applicationValidator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServicesException {
        try {
            HttpSession session = request.getSession();
            Long userId = (Long) (session.getAttribute(USER_ID));
            String id = request.getParameter(APPLICATION_ID);
            String dateCheckIn = request.getParameter(DATE_CHECK_IN);
            String dateCheckOut = request.getParameter(DATE_CHECK_OUT);
            String roomType = request.getParameter(ROOM_TYPE);
            String capacity = request.getParameter(CAPACITY);
            String status = request.getParameter(STATUS);
            String roomId = request.getParameter(ROOM_ID);
            String invoice = request.getParameter(INVOICE);
            if (!isValidApplication(dateCheckIn, dateCheckOut, roomType, capacity)) {
                request.setAttribute(ERROR_MESSAGE, INCORRECT_DATA_MESSAGE);
                return CommandResult.forward(BOOKING_PAGE);
            }

            Application application = createApplication(id, dateCheckIn, dateCheckOut, roomType, capacity, status, userId, roomId, invoice);
            applicationService.saveApplication(application);
        } catch (ParseException e) {
            throw new ServicesException(PARSING_ERROR, e);
        }
        return CommandResult.redirect(APPLICATION_HISTORY);
    }

    private Application createApplication(String id, String checkIn, String checkOut, String type, String capacity, String state, Long userId, String roomId, String invoice) throws ParseException {
        Long idApplication = Long.parseLong(id);
        LocalDate dateCheckIn = LocalDate.parse(checkIn);
        LocalDate dateCheckOut = LocalDate.parse(checkOut);
        RoomType roomType = RoomType.valueOf(type);
        byte personAmount = Byte.parseByte(capacity);
        ApplicationStatus applicationStatus = ApplicationStatus.valueOf(state);
        Long roomIdLong = Long.parseLong(roomId);
        BigDecimal invoiceBiGDecimal = new BigDecimal(invoice);
        return new Application(idApplication, dateCheckIn, dateCheckOut, roomType, personAmount, applicationStatus, userId, roomIdLong, invoiceBiGDecimal);
    }

    private boolean isValidApplication(String checkIn, String checkOut, String type, String capacity) {
        boolean isValidCheckIn = applicationValidator.isValidDateCheckIn(checkIn);
        boolean isValidCheckOut = applicationValidator.isValidDateCheckOut(checkIn, checkOut);
        boolean isValidType = applicationValidator.isValidRoomType(type);
        boolean isValidCapacity = applicationValidator.isValidCapacity(capacity);
        return isValidCheckIn && isValidCheckOut && isValidType && isValidCapacity;
    }

}
