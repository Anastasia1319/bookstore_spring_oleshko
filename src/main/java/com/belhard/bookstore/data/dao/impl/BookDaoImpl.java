package com.belhard.bookstore.data.dao.impl;

import com.belhard.bookstore.data.dao.BookDao;
import com.belhard.bookstore.data.entity.Book;
import com.belhard.bookstore.exceptions.NotUpdateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
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
    private static final Logger log = LogManager.getLogger(BookDaoImpl.class);

    @Autowired
    public BookDaoImpl(JdbcTemplate jdbcTemplate, BookRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }


    @Override
    public  List<Book> findAll() {
        log.info("Created a list of books matching the search criteria");
        return jdbcTemplate.query(SELECT_ALL, rowMapper);
    }

    @Override
    public Book findById(Long id) {
        log.info("Book with id {} found", id);
        return jdbcTemplate.queryForObject(FIND_BY_ID, rowMapper, id);
    }

    @Override
    public Book create(Book book) {
        log.info("Object creation method called");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(CREATE, new String[]{"id"});
            mapBookToStatementData(book, statement);
            return statement;
        }, keyHolder);
        return Optional.ofNullable(keyHolder.getKey())
                .map(Number::longValue)
                .map(this::findById)
                .orElseThrow();
    }
    @Override
    public Book update(Book book) {
        log.info("Trying to update a row with a book in the database");
        int rowsUpdated = jdbcTemplate.update(UPDATE, book.getAuthor(), book.getTitle(), book.getPublishinYear(), book.getIsbn(), book.getPrice(), book.getId());
        if (rowsUpdated == 0) {
            log.warn("Updated rows (books): 0");
            throw new NotUpdateException("Couldn't update book: {}" + book);
        }
        return findById(book.getId());
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
    public Book findByIsbn(String isbn) {
        log.info("Book with isbn {} found", isbn);
        return jdbcTemplate.queryForObject(FIND_BY_ISBN, rowMapper,isbn);
    }

    @Override
    public List<Book> findByAuthor(String author) {
        log.info("Created a list of books matching the search criteria");
        return jdbcTemplate.query(FIND_BY_AUTHOR, rowMapper, author);
    }

    @Override
    public long countAll() {
        log.info("Created a database call - counting the number of rows");
        return jdbcTemplate.queryForObject(COUNT_ALL, Integer.class);
    }

    private void mapBookToStatementData (Book book, PreparedStatement statement) throws SQLException {
        statement.setString(1, book.getAuthor());
        statement.setString(2, book.getTitle());
        statement.setInt(3, book.getPublishinYear());
        statement.setString(4, book.getIsbn());
        statement.setBigDecimal(5, book.getPrice());
        log.info("Object prepared for transfer to the database");
    }
}
