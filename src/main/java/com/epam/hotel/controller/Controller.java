package com.epam.hotel.controller;

import com.epam.hotel.command.Command;
import com.epam.hotel.command.CommandFactory;
import com.epam.hotel.command.CommandResult;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.epam.hotel.connection.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);
    private static final String COMMAND = "command";
    private static final String ERROR_PAGES_ERROR_500 = "WEB-INF/errorPages/error500.jsp";
    private static final String ERROR_PAGES_ERROR_404 = "WEB-INF/errorPages/error404.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String commandRequest = request.getParameter(COMMAND);
            Command action = CommandFactory.create(commandRequest);
            CommandResult commandResult = action.execute(request, response);
            String page = commandResult.getPage();
            if (commandResult.getIsRedirect()) {
                response.sendRedirect(page);
            } else {
                forwardPage(page, request, response);
            }
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage(), e);
            forwardPage(ERROR_PAGES_ERROR_404, request, response);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            forwardPage(ERROR_PAGES_ERROR_500, request, response);
        }

    }

    private void forwardPage(String page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.closeAllConnections();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        super.destroy();
    }
}
