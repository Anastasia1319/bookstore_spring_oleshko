package com.belhard.bookstore.controller.command.impl.book;

import com.belhard.bookstore.controller.command.Command;
import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.dto.BookServiceDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller("books")
@RequiredArgsConstructor
public class BooksCommand implements Command {
    private final BookService bookService;

    @Override
    public String execute(HttpServletRequest req) {
        int page = Integer.parseInt(req.getParameter("page"));
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        int pageSize = 5;
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        List<BookServiceDto> books = bookService.getAll(pageable);
        Long totalPages = bookService.totalPages(pageSize);
        String command = "books";
        req.setAttribute("books", books);
        req.setAttribute("totalPages", totalPages);
        req.setAttribute("page", page);
        req.setAttribute("command", command);
        return "jsp/books.jsp";
    }
}
