package com.belhard.bookstore.controller;

import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.dto.BookDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class BooksCommand implements Command{
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
