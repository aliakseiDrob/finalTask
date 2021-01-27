package com.epam.hotel.filters;


import com.epam.hotel.entity.enums.UserRole;

import javax.management.relation.Role;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {
    private static final String USER_ROLE = "userRole";
    private static final String COMMAND = "command";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String command = request.getParameter("command");
    UserRole userRole = (UserRole) session.getAttribute("userRole");
//      if (userRole == null) {
//            if ("authorization".equals(command)||"login".equals(command)||"mainPage".equals(command)) {
//                filterChain.doFilter(servletRequest, servletResponse);
//            } else {
//                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/allUsers.jsp");
//                dispatcher.forward(request,response);
//            }
//        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
