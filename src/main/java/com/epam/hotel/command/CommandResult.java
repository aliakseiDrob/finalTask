package com.epam.hotel.command;

import java.util.Objects;

public class CommandResult {
    private final String page;
    private final boolean isRedirect;

    private CommandResult(String page, boolean isRedirect) {
        this.page = page;
        this.isRedirect = isRedirect;
    }

    public static CommandResult forward(String page) {
        return new CommandResult(page, false);
    }

    public static CommandResult redirect(String page) {
        return new CommandResult(page, true);
    }

    public String getPage() {
        return page;
    }

    public boolean getIsRedirect() {
        return isRedirect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CommandResult that = (CommandResult) o;
        return isRedirect == that.isRedirect && Objects.equals(page, that.page);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, isRedirect);
    }

}
