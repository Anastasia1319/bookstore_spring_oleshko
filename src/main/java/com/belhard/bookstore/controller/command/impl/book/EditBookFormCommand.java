package com.belhard.bookstore.controller.command.impl.book;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.exceptions.ApplicationException;
import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.dto.BookDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller("edit_book_form")
@RequiredArgsConstructor
public class EditBookFormCommand implements Command {
    private final BookService bookService;

    @Override
    public String execute(HttpServletRequest req) {
        Long id = processReq(req);
        BookDto bookDto = bookService.getById(id);
        req.setAttribute("book", bookDto);
        return "jsp/edit_book.jsp";
    }

    private static long processReq(HttpServletRequest req) {
        try {
            String rawId = req.getParameter("id");
            return Long.parseLong(rawId);
        } catch (RuntimeException e) {
            throw new ApplicationException("Incorrect request data: " + e);
        }

    }
}
