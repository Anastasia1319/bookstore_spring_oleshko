package com.belhard.bookstore.controller.command.impl.book;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.exceptions.ApplicationException;
import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.dto.BookServiceDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller("book")
@RequiredArgsConstructor
public class BookCommand implements Command {
    private final BookService bookService;

    @Override
    public String execute(HttpServletRequest req) {
        Long id = processReq(req);
        BookServiceDto bookServiceDto = bookService.getById(id);
        req.setAttribute("book", bookServiceDto);
        return "jsp/book.jsp";
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
