package com.belhard.bookstore.data.dao.impl;

import com.belhard.bookstore.data.dao.BookDao;
import com.belhard.bookstore.data.dao.dto.BookDto;
import com.belhard.bookstore.data.entity.Book;
import com.belhard.bookstore.exceptions.NotUpdateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
@Log4j2
@RequiredArgsConstructor
public class BookDaoImpl implements BookDao {
    private static final String SELECT_ALL = "SELECT b.id, b.author, b.title, b.publishin_year, b.isbn, b.price FROM books b";
    private static final String FIND_BY_ID = "SELECT b.id, b.author, b.title, b.publishin_year, b.isbn, b.price FROM books b WHERE id = ?";
    private static final String CREATE = "INSERT INTO books (author, title, publishin_year, isbn, price) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE books SET author = ?, title = ?, publishin_year = ?, isbn = ?, price = ? WHERE id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM books WHERE id = ?";
    private static final String FIND_BY_ISBN = "SELECT b.id, b.author, b.title, b.publishin_year, b.isbn, b.price FROM books b WHERE isbn = ?";
    private static final String FIND_BY_AUTHOR = "SELECT b.id, b.author, b.title, b.publishin_year, b.isbn, b.price FROM books b WHERE author = ?";
    private static final String COUNT_ALL = "SELECT COUNT(*) FROM books";
    private final JdbcTemplate jdbcTemplate;
    private final BookRowMapper rowMapper;

    @Override
    public  List<BookDto> findAll() {
        log.info("Created a list of books matching the search criteria");
        return jdbcTemplate.query(SELECT_ALL, rowMapper);
    }

    @Override
    public BookDto findById(Long id) {
        log.info("Book with id {} found", id);
        return jdbcTemplate.queryForObject(FIND_BY_ID, rowMapper, id);
    }

    @Override
    public BookDto create(BookDto bookDto) {
        log.info("Object creation method called");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(CREATE, new String[]{"id"});
            mapBookToStatementData(bookDto, statement);
            return statement;
        }, keyHolder);
        return Optional.ofNullable(keyHolder.getKey())
                .map(Number::longValue)
                .map(this::findById)
                .orElseThrow();
    }
    @Override
    public BookDto update(BookDto bookDto) {
        log.info("Trying to update a row with a book in the database");
        int rowsUpdated = jdbcTemplate.update(UPDATE, bookDto.getAuthor(), bookDto.getTitle(), bookDto.getPublishinYear(), bookDto.getIsbn(), bookDto.getPrice(), bookDto.getId());
        if (rowsUpdated == 0) {
            log.warn("Updated rows (books): 0");
            throw new NotUpdateException("Couldn't update book: {}" + bookDto);
        }
        return findById(bookDto.getId());
    }

    @Override
    public boolean delete(Long id) {
        log.info("Trying to delete a row with a book in the database");
        int rowsUpdated = jdbcTemplate.update(DELETE_BY_ID, id);
        if (rowsUpdated == 0) {
            log.warn("Updated rows on deletion (books): 0");
            throw new NotUpdateException("Couldn't delete book with id: " + id);
        }
        return rowsUpdated == 1;
    }

    @Override
    public BookDto findByIsbn(String isbn) {
        log.info("Book with isbn {} found", isbn);
        return jdbcTemplate.queryForObject(FIND_BY_ISBN, rowMapper,isbn);
    }

    @Override
    public List<BookDto> findByAuthor(String author) {
        log.info("Created a list of books matching the search criteria");
        return jdbcTemplate.query(FIND_BY_AUTHOR, rowMapper, author);
    }

    @Override
    public long countAll() {
        log.info("Created a database call - counting the number of rows");
        return jdbcTemplate.queryForObject(COUNT_ALL, Integer.class);
    }

    private void mapBookToStatementData (BookDto bookDto, PreparedStatement statement) throws SQLException {
        statement.setString(1, bookDto.getAuthor());
        statement.setString(2, bookDto.getTitle());
        statement.setInt(3, bookDto.getPublishinYear());
        statement.setString(4, bookDto.getIsbn());
        statement.setBigDecimal(5, bookDto.getPrice());
        log.info("Object prepared for transfer to the database");
    }
}
