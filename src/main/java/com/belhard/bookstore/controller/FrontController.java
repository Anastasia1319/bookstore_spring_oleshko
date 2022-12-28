package com.belhard.bookstore.controller;

import com.belhard.bookstore.AppConfig;
import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.exceptions.ApplicationException;
import com.belhard.bookstore.exceptions.NotFoundException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.io.IOException;

@WebServlet("/controller")
public class FrontController extends HttpServlet {
    private AnnotationConfigApplicationContext context;
    private static final Logger log = LogManager.getLogger(FrontController.class);

    @Override
    public void init() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        log.info("Created context");
    }
    @Override
    public void destroy() {
        if (context != null) {
            context.close();
        }
        log.info("Context is closed");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page;
        try {
            Command command = context.getBean(req.getParameter("command"), Command.class);
            log.info("DoGet method is started");
            page = command.execute(req);
        } catch (NotFoundException e) {
            page = processNotFoundException(req, resp, e);
        } catch (ApplicationException e) {
            page = processApplicationException(req, resp, e);
        } catch (Exception e) {
            page = processException(req, resp, e);
        }
        req.getRequestDispatcher(page).forward(req, resp);
    }

    private String processNotFoundException(HttpServletRequest req, HttpServletResponse resp, NotFoundException e) {
        log.error(e.getClass().getSimpleName());
        req.setAttribute("Error_message", "Sorry! Nothing found. We're really sorry!");
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return context.getBean("error", Command.class).execute(req);
    }

    private String processApplicationException(HttpServletRequest req, HttpServletResponse resp, ApplicationException e) {
        log.error(e.getClass().getSimpleName());
        req.setAttribute("Error_message", "Sorry!... Incorrect request");
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return context.getBean("error", Command.class).execute(req);
    }
    private String processException(HttpServletRequest req, HttpServletResponse resp, Exception e) {
        log.error(e.getClass().getSimpleName());
        req.setAttribute("Error_message", "Sorry!... Incorrect request");
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return context.getBean("error", Command.class).execute(req);
    }
}
