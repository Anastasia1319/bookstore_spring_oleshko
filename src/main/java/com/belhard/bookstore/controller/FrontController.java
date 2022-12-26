package com.belhard.bookstore.controller;

import com.belhard.bookstore.AppConfig;
import com.belhard.bookstore.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.io.IOException;

@WebServlet("/controller")
public class FrontController extends HttpServlet {
    private AnnotationConfigApplicationContext context;

    @Override
    public void init() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
    }
    @Override
    public void destroy() {
        context.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("command");
        Command command = context.getBean(action, Command.class);
        String page;
        try {
            page = command.execute(req);
        } catch (Exception e) {
            req.setAttribute("Error_message", "Sorry!... Incorrect request");
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            page = context.getBean("error", Command.class).execute(req);
        }
        req.getRequestDispatcher(page).forward(req, resp);
    }
}
