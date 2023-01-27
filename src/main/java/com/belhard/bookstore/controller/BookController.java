package com.belhard.bookstore.controller;

import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("/{id}")
    public String getBook(@PathVariable Long id, Model model) {
        BookDto book = bookService.getById(id);
        model.addAttribute("book", book);
        return "book";
    }

    @GetMapping("/page={page}")
    public String getAll(@PathVariable Integer page, Model model) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        int pageSize = 5;
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        Long totalPages = bookService.totalPages(pageSize);
        List<BookDto> books = bookService.getAll(pageable);
        model.addAttribute("books", books);
        model.addAttribute("totalPages", totalPages);
        return "books";
    }

    @GetMapping("/create")
    public String createBookForm() {
    return "add_book";
    }

    @PostMapping("/create")
    public String createBook(@ModelAttribute BookDto book) {
        bookService.save(book);
        return "redirect:/books/" + book.getId();
    }

    @GetMapping("/edit/{id}")
    public  String editBookForm(@PathVariable Long id, Model model) {
        BookDto book = bookService.getById(id);
        model.addAttribute("book", book);
        return "edit_book";
    }

    @PostMapping("/edit/{id}")
    public String editBook(@ModelAttribute BookDto book) {
        bookService.save(book);
        return "redirect:/books/" + book.getId();
    }
}
