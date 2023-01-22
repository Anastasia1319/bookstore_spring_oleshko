package com.belhard.bookstore.controller.command.impl.book;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.dto.BookServiceDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;


@Controller("edit_book")
@RequiredArgsConstructor
public class EditBookCommand implements Command {
    private final BookService bookService;

    @Override
    public String execute(HttpServletRequest req) {
        BookServiceDto toEdit = processRequest(req);
        bookService.save(toEdit);
        BookServiceDto edited = bookService.getById(toEdit.getId());
        req.setAttribute("book", edited);
        return "jsp/book.jsp";
    }

    private BookServiceDto processRequest(HttpServletRequest req) {
        Long id = Long.parseLong(req.getParameter("id"));
        BookServiceDto toEdit = bookService.getById(id);
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        Integer publishing_year = Integer.valueOf(req.getParameter("publishing_year"));
        String isbn = req.getParameter("isbn");
        toEdit.setTitle(title);
        toEdit.setAuthor(author);
        toEdit.setPublishingYear(publishing_year);
        toEdit.setIsbn(isbn);
        return toEdit;
    }
}
