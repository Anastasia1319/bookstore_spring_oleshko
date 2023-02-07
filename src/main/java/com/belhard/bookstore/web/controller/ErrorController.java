package com.belhard.bookstore.web.controller;

import com.belhard.bookstore.platform.exceptions.ApplicationException;
import com.belhard.bookstore.platform.exceptions.NotFoundException;
import com.belhard.bookstore.platform.exceptions.NotUpdateException;
import com.belhard.bookstore.platform.exceptions.SecurityException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@ControllerAdvice
@RequestMapping("/error")
@Log4j2
public class ErrorController {

    @GetMapping
    public String error() {
        log.error("Error from Server");
        return "error";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String applicationError(NotFoundException e, Model model) {
        log.error(e);
        model.addAttribute("message", e.getMessage());
        return "error";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String applicationError(NotUpdateException e, Model model) {
        log.error(e);
        model.addAttribute("message", e.getMessage());
        return "error";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String applicationError(ApplicationException e, Model model) {
        log.error(e);
        model.addAttribute("message", e.getMessage());
        return "error";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String applicationError(SecurityException e, Model model) {
        log.error(e);
        model.addAttribute("message", e.getMessage());
        return "error";
    }
}
