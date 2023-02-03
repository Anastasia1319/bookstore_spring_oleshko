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
        totalPages = bookService.getTotalPages(pageSize);
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

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books/all/0";
    }

    @PostMapping ("/find")
    public String getByAuthor(@RequestParam String author, @RequestParam Integer page, Model model) {
        pageable = PageRequest.of(page, pageSize, sort);
        totalPages = bookService.getTotalPagesAuthor(pageSize, author);
        List<BookDto> books = bookService.getByAuthor(author, pageable);
        model.addAttribute("books", books);
        model.addAttribute("totalPages", totalPages);
        return "books";
    }
}
