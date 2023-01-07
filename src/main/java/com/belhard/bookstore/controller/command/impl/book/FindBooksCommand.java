package com.belhard.bookstore.controller.command.impl.book;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.dto.BookDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller("find_books")
public class FindBooksCommand implements Command {
    private final BookService bookService;

    public FindBooksCommand(BookService bookService) {
        this.bookService = bookService;
    }


    @Override
    public String execute(HttpServletRequest req) {
        String author = req.getParameter("author");
        List<BookDto> books = bookService.getByAuthor(author);
        req.setAttribute("books", books);
        return "jsp/books.jsp";
    }
}
