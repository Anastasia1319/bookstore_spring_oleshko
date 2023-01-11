package com.belhard.bookstore.controller.command.impl.book;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.dto.BookServiceDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

@Controller("add_book")
@RequiredArgsConstructor
public class AddBookCommand implements Command {
    private final BookService bookService;

    @Override
    public String execute(HttpServletRequest req) {
        BookServiceDto toCreate = processRequest(req);
        BookServiceDto created = bookService.create(toCreate);
        req.setAttribute("book", created);
        return "jsp/book.jsp";
    }

    private static BookServiceDto processRequest(HttpServletRequest req) {
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        Integer publishing_year = Integer.valueOf(req.getParameter("publishing_year"));
        String isbn = req.getParameter("isbn");
        BigDecimal price = BigDecimal.valueOf(Double.valueOf(req.getParameter("price")));
        BookServiceDto toCreate = new BookServiceDto();
        toCreate.setTitle(title);
        toCreate.setAuthor(author);
        toCreate.setPublishinYear(publishing_year);
        toCreate.setIsbn(isbn);
        toCreate.setPrice(price);
        return toCreate;
    }
}
