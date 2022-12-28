package com.belhard.bookstore.controller.command.impl.book;

import com.belhard.bookstore.controller.command.Command;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

@Controller("add_book_form")
public class AddBookFormCommand implements Command {

    @Override
    public String execute(HttpServletRequest req) {
        return "jsp/add_book.jsp";
    }
}
