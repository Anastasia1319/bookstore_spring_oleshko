package com.belhard.bookstore.controller;

import com.belhard.bookstore.AppConfig;
import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.exceptions.ApplicationException;
import com.belhard.bookstore.exceptions.NotFoundException;
import com.belhard.bookstore.exceptions.NotUpdateException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.io.IOException;

@WebServlet("/controller")
@Log4j2
public class FrontController extends HttpServlet {
    private AnnotationConfigApplicationContext context;

    @Override
    public void init() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
    }
    @Override
    public void destroy() {
        if (context != null) {
            context.close();
        }
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
        } catch (NotUpdateException e) {
            page = processNotUpdateException(req, resp, e);
        }catch (Exception e) {
            page = processException(req, resp, e);
        }
        req.getRequestDispatcher(page).forward(req, resp);
    }

    private String processNotFoundException(HttpServletRequest req, HttpServletResponse resp, NotFoundException e) {
        log.error("NotFoundException from FrontController", e);
        req.setAttribute("Error_message", "Sorry! Nothing found. We're really sorry!");
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return context.getBean("error", Command.class).execute(req);
    }

    private String processApplicationException(HttpServletRequest req, HttpServletResponse resp, ApplicationException e) {
        log.error("ApplicationException from FrontController", e);
        req.setAttribute("Error_message", "Sorry!... Incorrect request");
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return context.getBean("error", Command.class).execute(req);
    }
    private String processNotUpdateException(HttpServletRequest req, HttpServletResponse resp, NotUpdateException e) {
        log.error("NotUpdateException from FrontController", e);
        req.setAttribute("Error_message", "Sorry!... Incorrect request");
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return context.getBean("error", Command.class).execute(req);
    }
    private String processException(HttpServletRequest req, HttpServletResponse resp, Exception e) {
        log.error("Exception from FrontController", e);
        req.setAttribute("Error_message", "Sorry!... Incorrect request");
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return context.getBean("error", Command.class).execute(req);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
