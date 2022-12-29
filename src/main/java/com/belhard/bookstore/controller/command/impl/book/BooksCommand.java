package com.belhard.bookstore.controller.command.impl.book;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.dto.BookDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller("books")
public class BooksCommand implements Command {
    private final BookService bookService;

    public BooksCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public String execute(HttpServletRequest req) {
        List<BookDto> books = bookService.getAll();
        req.setAttribute("books", books);
        return "jsp/books.jsp";
    }
}
