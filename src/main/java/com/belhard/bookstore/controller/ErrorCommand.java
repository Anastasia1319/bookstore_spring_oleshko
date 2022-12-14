package com.belhard.bookstore.controller;

import jakarta.servlet.http.HttpServletRequest;

public class ErrorCommand implements Command{
    public ErrorCommand() {
    }

    @Override
    public String execute(HttpServletRequest req) {
        return "jsp/error.jsp";
    }
}
