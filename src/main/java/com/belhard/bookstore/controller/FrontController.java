package com.belhard.bookstore.controller;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.controller.command.impl.CommandFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/controller")
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("command");
        CommandFactory commandFactory = CommandFactory.INSTANCE;
        Command command = commandFactory.getCommand(action);
        String page;
        try {
            page = command.execute(req);
        } catch (Exception e) {
            req.setAttribute("Error_message", "Sorry!... Incorrect request");
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            page = commandFactory.getCommand("error").execute(req);
        }
        req.getRequestDispatcher(page).forward(req, resp);
    }
}
