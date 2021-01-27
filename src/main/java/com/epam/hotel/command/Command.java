package com.epam.hotel.command;

import com.epam.hotel.exception.ServicesException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServicesException;
}
