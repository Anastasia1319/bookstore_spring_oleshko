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
    private final Sort sort = Sort.by(Sort.Direction.ASC, "id");
    private final Integer pageSize = 5;
    private Pageable pageable;
    private Long totalPages;

    @GetMapping("/{id}")
    public String getBook(@PathVariable Long id, Model model) {
        BookDto book = bookService.getById(id);
        model.addAttribute("book", book);
        return "book";
    }

    @GetMapping("/all/{page}")
    public String getAll(@PathVariable Integer page, Model model) {
        pageable = PageRequest.of(page, pageSize, sort);
        totalPages = bookService.totalPages(pageSize);
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
        BookDto created = bookService.save(book);
        return "redirect:/books/" + created.getId();
    }
}
