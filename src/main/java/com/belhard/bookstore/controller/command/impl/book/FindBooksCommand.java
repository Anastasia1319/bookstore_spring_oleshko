package com.belhard.bookstore.controller.command.impl.book;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.dto.BookDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller("find_books")
@RequiredArgsConstructor
public class FindBooksCommand implements Command {
    private final BookService bookService;

    @Override
    public String execute(HttpServletRequest req) {
        String author = req.getParameter("author");
        List<BookDto> books = bookService.getByAuthor(author);
        req.setAttribute("books", books);
        return "jsp/books.jsp";
    }
}
