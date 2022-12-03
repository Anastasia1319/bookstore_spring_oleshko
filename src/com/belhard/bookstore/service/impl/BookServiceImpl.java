package com.belhard.bookstore.service.impl;

import com.belhard.bookstore.data.dao.BookDao;
import com.belhard.bookstore.service.BookService;
import com.belhard.bookstore.service.dto.BookDto;

import java.util.List;

public class BookServiceImpl implements BookService {
    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public List<BookDto> getAll() {
        return null;
    }

    @Override
    public BookDto getById(Long id) {
        return null;
    }

    @Override
    public BookDto create(BookDto dto) {
        return null;
    }

    @Override
    public BookDto update(BookDto dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
