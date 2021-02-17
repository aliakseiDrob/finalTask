package com.epam.hotel.filters;

import com.epam.hotel.entity.enums.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ValidationFilter implements Filter {
    private static final String USER_ROLE = "userRole";
    private static final String COMMAND = "command";
    private static final int FORBIDDEN_ERROR_CODE = 403;
    private List<String> adminCommand;
    private List<String> guestCommand;

    @Override
    public void init(FilterConfig filterConfig) {
        adminCommand = Arrays.asList("inProgressApplication", "addRoom", "blockUnBlockUser", "blockUnblockRoom", "deleteUser",
                "availableRooms", "addInvoice", "editRoomPage", "editRoom", "allRooms", "allUsers", "addRoomPage");
        guestCommand = Arrays.asList("booking", "applicationHistory", "bookingPage","guestInvoices","guestInvoicesPage");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String command = request.getParameter(COMMAND);
        UserRole userRole = (UserRole) session.getAttribute(USER_ROLE);
        if (!UserRole.ADMINISTRATOR.equals(userRole) && adminCommand.contains(command)) {
            response.sendError(FORBIDDEN_ERROR_CODE);
        }
        if (!UserRole.GUEST.equals(userRole) && guestCommand.contains(command)) {
            response.sendError(FORBIDDEN_ERROR_CODE);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
