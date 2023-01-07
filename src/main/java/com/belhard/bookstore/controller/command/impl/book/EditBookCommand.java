package com.belhard.bookstore.controller.command.impl.book;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.dto.BookDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;


@Controller("edit_book")
public class EditBookCommand implements Command {
    private final BookService bookService;

    public EditBookCommand(BookService bookService) {
        this.bookService = bookService;
    }


    @Override
    public String execute(HttpServletRequest req) {
        BookDto toEdit = processRequest(req);
        BookDto edited = bookService.update(toEdit);
        req.setAttribute("book", edited);
        return "jsp/book.jsp";
    }

    private BookDto processRequest(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id"));
        BookDto toEdit = bookService.getById(id);
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        Integer publishing_year = Integer.valueOf(req.getParameter("publishing_year"));
        String isbn = req.getParameter("isbn");
        toEdit.setTitle(title);
        toEdit.setAuthor(author);
        toEdit.setPublishinYear(publishing_year);
        toEdit.setIsbn(isbn);
        return toEdit;
    }
}
