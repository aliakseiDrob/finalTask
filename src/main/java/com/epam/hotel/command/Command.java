package com.epam.hotel.command;

import com.epam.hotel.exception.ServicesException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * The interface describes any action performed on the server side of the application.
 */
public interface Command {
    /**
     * Performs the necessary actions using the parameters of HttpServletRequest object
     * and returns CommandResult object with further page jump instructions
     *
     * @param request HttpServletRequest entity of the current request
     * @return CommandResult object with page path and redirection type
     * @throws ServicesException in case of logical errors
     */
    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServicesException;
}
