package com.belhard.bookstore.controller.command.impl.book;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.dto.BookDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

@Controller("add_book")
public class AddBookCommand implements Command {
    private final BookService bookService;

    public AddBookCommand(BookService bookService) {
        this.bookService = bookService;
    }


    @Override
    public String execute(HttpServletRequest req) {
        BookDto toCreate = processRequest(req);
        BookDto created = bookService.create(toCreate);
        req.setAttribute("book", created);
        return "jsp/book.jsp";
    }

    private static BookDto processRequest(HttpServletRequest req) {
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        Integer publishing_year = Integer.valueOf(req.getParameter("publishing_year"));
        String isbn = req.getParameter("isbn");
        BigDecimal price = BigDecimal.valueOf(Double.valueOf(req.getParameter("price")));
        BookDto toCreate = new BookDto();
        toCreate.setTitle(title);
        toCreate.setTitle(author);
        toCreate.setPublishinYear(publishing_year);
        toCreate.setIsbn(isbn);
        toCreate.setPrice(price);
        return toCreate;
    }
}
