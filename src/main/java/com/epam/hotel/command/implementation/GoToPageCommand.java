package com.epam.hotel.command.implementation;

import com.epam.hotel.command.Command;
import com.epam.hotel.command.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoToPageCommand implements Command {
    private final String page;

    public GoToPageCommand(String page) {

        this.page = page;
    }

    public String getPage() {

        return page;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        return CommandResult.forward(getPage());
    }
}
