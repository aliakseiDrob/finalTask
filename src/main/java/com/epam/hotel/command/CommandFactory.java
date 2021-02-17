package com.epam.hotel.command;

import com.epam.hotel.command.implementation.*;
import com.epam.hotel.dao.DaoHelperFactory;
import com.epam.hotel.logic.service.*;
import com.epam.hotel.logic.validators.ApplicationValidatorImpl;
import com.epam.hotel.logic.validators.RoomValidatorImpl;
import com.epam.hotel.logic.validators.UserValidator;

public class CommandFactory {
    private static final String AUTHORIZATION_COMMAND = "authorization";
    private static final String LOGIN_COMMAND = "login";
    private static final String LOGOUT_COMMAND = "logout";
    private static final String BOOKING_COMMAND = "booking";
    private static final String GUEST_APPLICATIONS_COMMAND = "applicationHistory";
    private static final String GUEST_INVOICES_COMMAND = "guestInvoices";
    private static final String IN_PROGRESS_APPLICATION_COMMAND = "inProgressApplication";
    private static final String ADD_ROOM_COMMAND = "addRoom";
    private static final String BLOCK_UNBLOCK_USER_COMMAND = "blockUnBlockUser";
    private static final String BLOCK_UNBLOCK_ROOM_COMMAND = "blockUnblockRoom";
    private static final String DELETE_USER_COMMAND = "deleteUser";
    private static final String AVAILABLE_ROOMS_COMMAND = "availableRooms";
    private static final String ADD_INVOICE_COMMAND = "addInvoice";
    private static final String EDIT_ROOM_PAGE_COMMAND = "editRoomPage";
    private static final String EDIT_ROOM_COMMAND = "editRoom";

    private static final String ALL_ROOMS_COMMAND = "allRooms";
    private static final String ALL_USERS_COMMAND = "allUsers";
    private static final String ALL_APPLICATIONS_COMMAND = "allApplications";
    private static final String ALL_INVOICES_COMMAND = "allInvoices";

    private static final String CONTACTS_PAGE = "contactsPage";
    private static final String ABOUT_US_PAGE = "aboutUsPage";
    private static final String MAIN_PAGE = "mainPage";
    private static final String BOOKING_PAGE = "bookingPage";
    private static final String ADD_ROOM_PAGE = "addRoomPage";

    public static Command create(String command) {
        switch (command) {
            case LOGIN_COMMAND:
                return new LoginCommand(new UserServiceImpl(new DaoHelperFactory()), new UserValidator());
            case LOGOUT_COMMAND:
                return new LogOutCommand();
            case BOOKING_COMMAND:
                return new BookingCommand(new ApplicationServiceImpl(new DaoHelperFactory()), new ApplicationValidatorImpl());
            case GUEST_APPLICATIONS_COMMAND:
                return new GuestApplicationsCommand(new ApplicationServiceImpl(new DaoHelperFactory()));
            case GUEST_INVOICES_COMMAND:
                return new GuestInvoicesCommand(new ApplicationDtoServiceImpl(new DaoHelperFactory()));
            case ADD_ROOM_COMMAND:
                return new AddRoomCommand(new RoomServiceImpl(new DaoHelperFactory()), new RoomValidatorImpl());
            case BLOCK_UNBLOCK_USER_COMMAND:
                return new BlockUnblockUserCommand(new UserServiceImpl(new DaoHelperFactory()));
            case BLOCK_UNBLOCK_ROOM_COMMAND:
                return new BlockUnblockRoomCommand(new RoomServiceImpl(new DaoHelperFactory()));
            case DELETE_USER_COMMAND:
                return new DeleteUserCommand(new UserServiceImpl(new DaoHelperFactory()));
            case AVAILABLE_ROOMS_COMMAND:
                return new AvailableRoomsCommand(new ApplicationServiceImpl(new DaoHelperFactory()), new RoomServiceImpl(new DaoHelperFactory()));
            case ADD_INVOICE_COMMAND:
                return new AddInvoiceCommand(new ApplicationRoomServiceImpl(new DaoHelperFactory()));
            case EDIT_ROOM_PAGE_COMMAND:
                return new EditRoomPageCommand(new RoomServiceImpl(new DaoHelperFactory()));
            case EDIT_ROOM_COMMAND:
                return new EditRoomCommand(new RoomServiceImpl(new DaoHelperFactory()), new RoomValidatorImpl());

            case IN_PROGRESS_APPLICATION_COMMAND:
                return new InProgressApplicationsCommand(new ApplicationServiceImpl(new DaoHelperFactory()));
            case ALL_ROOMS_COMMAND:
                return new AllRoomsCommand(new RoomServiceImpl(new DaoHelperFactory()));
            case ALL_USERS_COMMAND:
                return new AllUsersCommand(new UserDtoServiceImpl(new DaoHelperFactory()));
            case ALL_APPLICATIONS_COMMAND:
                return new AllApplicationsCommand(new ApplicationServiceImpl(new DaoHelperFactory()));
            case ALL_INVOICES_COMMAND:
                return new AllInvoicesCommand(new ApplicationDtoServiceImpl(new DaoHelperFactory()));


            case AUTHORIZATION_COMMAND:
                return new GoToPageCommand("WEB-INF/view/login.jsp");
            case MAIN_PAGE:
                return new GoToPageCommand("WEB-INF/view/main.jsp");
            case CONTACTS_PAGE:
                return new GoToPageCommand("WEB-INF/view/contacts.jsp");
            case ABOUT_US_PAGE:
                return new GoToPageCommand("WEB-INF/view/aboutUs.jsp");
            case BOOKING_PAGE:
                return new GoToPageCommand("WEB-INF/view/bookingPage.jsp");
            case ADD_ROOM_PAGE:
                return new GoToPageCommand("WEB-INF/view/addRoom.jsp");

            default:
                throw new IllegalArgumentException("illegal argument");
        }
    }
}
