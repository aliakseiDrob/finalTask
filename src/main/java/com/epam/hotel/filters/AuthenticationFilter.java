package com.epam.hotel.filters;

import com.epam.hotel.entity.enums.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthenticationFilter implements Filter {
    private static final String USER_ROLE = "userRole";
    private static final String COMMAND = "command";
    private static final int UNAUTHORIZED_ERROR_CODE = 401;
    private List<String> unauthorizedGuestsCommand;

    @Override
    public void init(FilterConfig filterConfig) {
        unauthorizedGuestsCommand = Arrays.asList("authorization", "login", "logout", "mainPage", "aboutUsPage", "contactsPage");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String command = request.getParameter(COMMAND);
        UserRole userRole = (UserRole) session.getAttribute(USER_ROLE);
        if (command != null && userRole == null) {
            if (unauthorizedGuestsCommand.contains(command)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                response.sendError(UNAUTHORIZED_ERROR_CODE);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
